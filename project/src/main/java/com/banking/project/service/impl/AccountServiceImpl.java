package com.banking.project.service.impl;

import com.banking.project.dto.AccountDto;
import com.banking.project.dto.LoanDto;
import com.banking.project.dto.DebitCardDto;
import com.banking.project.dto.SafeDto;
import com.banking.project.dto.TransactionDto;
import com.banking.project.entity.Account;
import com.banking.project.entity.Safe;
import com.banking.project.entity.Transaction;
import com.banking.project.exception.exists.SafeAlreadyExistsException;
import com.banking.project.exception.notfound.AccountNotFoundException;
import com.banking.project.exception.notfound.SafeNotFoundException;
import com.banking.project.exception.notfound.TransactionNotFoundException;
import com.banking.project.exception.validation.NotEnoughFundsException;
import com.banking.project.exception.validation.WrongCreditAmountInputException;
import com.banking.project.repository.AccountRepository;
import com.banking.project.repository.specification.AccountSpecification;
import com.banking.project.service.AccountService;
import com.banking.project.service.DebitCardService;
import com.banking.project.service.SafeService;
import com.banking.project.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.banking.project.constant.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final DebitCardService debitCardService;
    private final SafeService safeService;
    private final TransactionService transactionService;
    private final ModelMapper mapper;


    @Override
    public boolean doesIbanExist(final String iban) {
        return accountRepository.exists(AccountSpecification.ibanLike(iban));
    }

    @Override
    public Long saveAccount(final Account account) {
        final Account accountSaved = accountRepository.save(account);
        return accountSaved.getId();
    }

    @Override
    public SafeDto createSafeForAccount(final String iban, final SafeDto safeDto) {
        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));

        if (safeService.doesNameExist(safeDto.getName())) {
            throw new SafeAlreadyExistsException(SAFE_ALREADY_EXISTS_MESSAGE);
        }

        final Safe safe = Safe.builder()
                .name(safeDto.getName())
                .key(safeDto.getKey())
                .initialFunds(safeDto.getInitialFunds())
                .creationDate(LocalDate.now())
                .build();

        if ((safeDto.getInitialFunds().compareTo(BigDecimal.ZERO) == 0) || (safe.getInitialFunds().compareTo(account.getAvailableAmount()) > 0)) {
            throw new NotEnoughFundsException(NOT_ENOUGH_FUNDS_MESSAGE);
        }

        account.getSafes().add(safe);
        final BigDecimal reduceAvailableAmount = account.getAvailableAmount().subtract(safe.getInitialFunds());
        account.setAvailableAmount(reduceAvailableAmount);


        accountRepository.save(account);

        return mapper.map(safe, SafeDto.class);
    }

    @Override
    public void deleteSafeByNameAndIban(final String name, final String iban) {
        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));

        final List<Safe> safeList = account.getSafes();
        final Safe foundSafe = safeList
                .stream()
                .filter(safe -> safe.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new SafeNotFoundException(SAFE_NOT_FOUND_MESSAGE));

        final BigDecimal initialFunds = foundSafe.getInitialFunds();
        final LocalDate creationDate = foundSafe.getCreationDate();
        final BigDecimal yearsDifference = BigDecimal.valueOf(ChronoUnit.YEARS.between(creationDate, LocalDate.now()));


        if (yearsDifference.equals(BigDecimal.ZERO)) {
            account.setAvailableAmount(account.getAvailableAmount().add(initialFunds));
        } else {
            BigDecimal annualInterestRate = BigDecimal.valueOf(0.01);
            BigDecimal ratePlusOne = BigDecimal.ONE.add(annualInterestRate);
            BigDecimal newFunds = initialFunds.multiply(ratePlusOne.pow(yearsDifference.intValue()));
            account.setAvailableAmount(account.getAvailableAmount().add(newFunds));
        }

        safeList.remove(foundSafe);

        accountRepository.save(account);

    }

    @Override
    public AccountDto getAccountByIban(final String iban) {
        final Account account = accountRepository.findAccountByIban(iban)
                .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));

        return mapper.map(account, AccountDto.class);

    }

    @Override
    public List<TransactionDto> getAccountTransactions(final String iban) {
        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));
        final List<Transaction> transactions = account.getTransactions();

        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException(TRANSACTION_NOT_FOUND_MESSAGE);
        }

        return transactions.stream()
                .map(transaction -> mapper.map(transaction, TransactionDto.class))
                .toList();
    }

    @Override
    public List<SafeDto> getAccountSafes(final String iban) {
        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));
        final List<Safe> safes = account.getSafes();

        if (safes.isEmpty()) {
            throw new SafeNotFoundException(SAFE_NOT_FOUND_MESSAGE);
        }
        return safes.stream()
                .map(safe -> mapper.map(safe, SafeDto.class))
                .toList();
    }


    @Override
    public TransactionDto  sendMoney(final String senderIban, final TransactionDto transactionDto) {
        final BigDecimal amount = transactionDto.getSentAmount();
        final String receiverIban = transactionDto.getReceiverIban();

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException(NON_ENOUGH_AMOUNT_MESSAGE);
        }
        final Account accountSender = accountRepository.findAccountByIban(senderIban).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));
        if (accountSender.getAvailableAmount().compareTo(amount) < 0) {
            throw new NotEnoughFundsException(NON_ENOUGH_AMOUNT_MESSAGE);
        }

        final Optional<Account> accountReceiverOptional = accountRepository.findAccountByIban(receiverIban);
        if (accountReceiverOptional.isPresent()) {
            final Account accountReceiver = accountReceiverOptional.get();
            accountReceiver.setAvailableAmount(accountReceiver.getAvailableAmount().add(amount));
            accountRepository.save(accountReceiver);
        }


        final Transaction transaction = Transaction.builder().receiverIban(receiverIban).sentAmount(amount.negate()).reason(transactionDto.getReason()).issueDate(LocalDateTime.now()).build();
        accountSender.getTransactions().add(transaction);

        BigDecimal reduceAmount = autoTransaction(accountSender,amount);
        accountSender.setAvailableAmount(accountSender.getAvailableAmount().subtract(reduceAmount));

        accountRepository.save(accountSender);

        return  mapper.map(transaction,TransactionDto.class);
    }

    @Override
    public TransactionDto takeLoan(final LoanDto loanDto) {
        final BigDecimal amount = loanDto.getCreditAmount();

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException(NON_ENOUGH_AMOUNT_MESSAGE);
        }
        final Account account = accountRepository.findAccountByIban(loanDto.getUserIban()).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));

        account.setCreditAmount(account.getCreditAmount().add(amount));
        account.setAvailableAmount(account.getAvailableAmount().add(amount));

        final Transaction transaction = Transaction.builder().sentAmount(amount).reason("Taking a loan").issueDate(LocalDateTime.now()).build();
        account.getTransactions().add(transaction);


        accountRepository.save(account);
        return  mapper.map(transaction,TransactionDto.class);
    }

    @Override
    public TransactionDto returnLoan(final LoanDto loanDto) {
        final BigDecimal amount = loanDto.getCreditAmount();

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException(NON_ENOUGH_AMOUNT_MESSAGE);
        }

        final Account account = accountRepository.findAccountByIban(loanDto.getUserIban()).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));
        final BigDecimal creditAmount = account.getCreditAmount();

        if (creditAmount == null || creditAmount.equals(BigDecimal.ZERO)) {
            throw new WrongCreditAmountInputException(WRONG_CREDIT_AMOUNT_INPUT_MESSAGE);
        }

        if (creditAmount.compareTo(amount) < 0) {
            throw new WrongCreditAmountInputException(WRONG_CREDIT_AMOUNT_INPUT_MESSAGE);

        }
        if (account.getAvailableAmount().compareTo(amount) < 0) {
            throw new NotEnoughFundsException(NON_ENOUGH_AMOUNT_MESSAGE);
        }

        account.setAvailableAmount(account.getAvailableAmount().subtract(amount));
        account.setCreditAmount(account.getCreditAmount().subtract(amount));
        final Transaction transaction = Transaction.builder().sentAmount(amount.negate()).reason("Returning a loan").issueDate(LocalDateTime.now()).creditPayment(true).build();
        account.getTransactions().add(transaction);

        accountRepository.save(account);
        return  mapper.map(transaction,TransactionDto.class);
    }

    @Override
    public DebitCardDto getDebitCardByIban(final String iban) {
        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));

        return mapper.map(account.getDebitCard(), DebitCardDto.class);

    }

    @Override
    public void updateSafe(final String iban, final String name, final BigDecimal funds) {
        if (funds.compareTo(BigDecimal.ZERO) < 0 || funds.compareTo(BigDecimal.ZERO) == 0) {
            throw new NotEnoughFundsException("New funds should be greater than zero!");
        }
        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));


        final Optional<Safe> safeOptional = account.getSafes().stream().filter(foundSafe -> foundSafe.getName().equals(name)).findFirst();

        if (safeOptional.isEmpty()) {
            throw new SafeNotFoundException(SAFE_NOT_FOUND_MESSAGE);
        }

        final Safe safe = safeOptional.get();

        safe.setInitialFunds(safe.getInitialFunds().add(funds));

        safeService.saveSafe(safe);

    }

    private BigDecimal autoTransaction(Account account, BigDecimal amount) {
        final Transaction autoTransaction = Transaction.builder().sentAmount(BigDecimal.valueOf(1)).reason("Transaction tax").issueDate(LocalDateTime.now()).build();
        account.getTransactions().add(autoTransaction);
        return amount.add(autoTransaction.getSentAmount());
    }

}
