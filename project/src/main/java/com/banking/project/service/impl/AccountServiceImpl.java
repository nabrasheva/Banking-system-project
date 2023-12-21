package com.banking.project.service.impl;

import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Account;
import com.banking.project.entity.Safe;
import com.banking.project.entity.Transaction;
import com.banking.project.repository.AccountRepository;
import com.banking.project.repository.specification.AccountSpecification;
import com.banking.project.service.AccountService;
import com.banking.project.service.DebitCardService;
import com.banking.project.service.SafeService;
import com.banking.project.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final DebitCardService debitCardService;
    private final SafeService safeService;
    private final TransactionService transactionService;


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
    public Long createSafeForAccount(final Long accountId, final SafeDto safeDto) {
        final Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account with this id doesn't exist!"));

        if (safeService.doesNameExist(safeDto.getName())) {
            throw new IllegalArgumentException("Safe with this name exists!");
        }

        final Safe safe = Safe.builder()
                .name(safeDto.getName())
                .key(safeDto.getKey())
                .initialFunds(safeDto.getInitialFunds())
                .build();

        account.getSafes().add(safe);

        accountRepository.save(account);

        return safe.getId();
    }

    @Override
    public void deleteSafeByNameAndIban(final String name, final String iban) {
        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new RuntimeException("Account with this id doesn't exist!"));

        final List<Safe> safeList = account.getSafes();
        final Safe foundSafe = safeList
                .stream()
                .filter(safe -> safe.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Safe with name %s doesn't exist in the db.", name)));
        
        final BigDecimal initialFunds = foundSafe.getInitialFunds();
        final LocalDate creationDate = foundSafe.getCreationDate();
        final BigDecimal yearsDifference = BigDecimal.valueOf(ChronoUnit.YEARS.between(creationDate, LocalDate.now()));

        final BigDecimal newFunds = yearsDifference.multiply(initialFunds).multiply(BigDecimal.valueOf(0.5));

        safeList.remove(foundSafe);
        account.setAvailableAmount(account.getAvailableAmount().add(newFunds));

        accountRepository.save(account);

    }

    @Override
    public void sendMoney(final String senderIban, final String receiverIban, final BigDecimal amount, final String reason) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Amount should be bigger than 0");
        }
        final Account accountSender = accountRepository.findAccountByIban(senderIban).orElseThrow(() -> new RuntimeException("Account with this iban doesn't exist!"));
        if (accountSender.getAvailableAmount().compareTo(amount) < 0) {
            throw new RuntimeException("There is not enough money in the account!");
        }

        final Optional<Account> accountReceiverOptional = accountRepository.findAccountByIban(receiverIban);
        if (accountReceiverOptional.isPresent()) {
            final Account accountReceiver = accountReceiverOptional.get();
            accountReceiver.setAvailableAmount(accountReceiver.getAvailableAmount().add(amount));
            accountRepository.save(accountReceiver);
        }

        accountSender.getAvailableAmount().subtract(amount);

        final Transaction transaction = Transaction.builder().receiverIban(receiverIban).sentAmount(amount.negate()).reason(reason).issueDate(LocalDateTime.now()).build();
        accountSender.getTransactions().add(transaction);

        accountRepository.save(accountSender);

    }

    @Override
    public void takeLoan(final String iban, final BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Amount should be bigger than 0");
        }
        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new RuntimeException("Account with this iban doesn't exist!"));

        account.setCreditAmount(account.getCreditAmount().add(amount));
        account.setAvailableAmount(account.getAvailableAmount().add(amount));

        final Transaction transaction = Transaction.builder().sentAmount(amount).reason("Taking a loan").issueDate(LocalDateTime.now()).build();
        account.getTransactions().add(transaction);

        accountRepository.save(account);

    }

    @Override
    public void returnLoan(final String iban, final BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Amount should be bigger than 0");
        }

        final Account account = accountRepository.findAccountByIban(iban).orElseThrow(() -> new RuntimeException("Account with this iban doesn't exist!"));
        final BigDecimal creditAmount = account.getCreditAmount();

        if (creditAmount == null || creditAmount.equals(BigDecimal.ZERO)) {
            throw new RuntimeException("There is no credit that should be returned!");
        }

        if (creditAmount.compareTo(amount) < 0) {
            throw new RuntimeException("The credit that should be returned is less than the requested amount!");

        }
        if (account.getAvailableAmount().compareTo(amount) < 0) {
            throw new RuntimeException("There is not enough money in the account!");
        }

        account.setAvailableAmount(account.getAvailableAmount().subtract(amount));
        account.setCreditAmount(account.getCreditAmount().subtract(amount));
        final Transaction transaction = Transaction.builder().sentAmount(amount.negate()).reason("Taking a loan").issueDate(LocalDateTime.now()).build();
        account.getTransactions().add(transaction);

        accountRepository.save(account);
    }
}
