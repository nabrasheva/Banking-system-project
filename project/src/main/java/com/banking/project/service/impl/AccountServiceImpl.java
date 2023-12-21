package com.banking.project.service.impl;

import com.banking.project.dto.AccountDto;
import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Account;
import com.banking.project.entity.Safe;
import com.banking.project.exception.exists.SafeAlreadyExistsException;
import com.banking.project.exception.notfound.AccountNotFoundException;
import com.banking.project.exception.notfound.SafeNotFoundException;
import com.banking.project.exception.validation.NotEnoughFundsException;
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
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    public Long createSafeForAccount(final Long accountId, final SafeDto safeDto) {
        final Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE));

        if (safeService.doesNameExist(safeDto.getName())) {
            throw new SafeAlreadyExistsException(SAFE_ALREADY_EXISTS_MESSAGE);
        }

        final Safe safe = Safe.builder()
                .name(safeDto.getName())
                .key(safeDto.getKey())
                .initialFunds(safeDto.getInitialFunds())
                .build();

        if ((safeDto.getInitialFunds().compareTo(BigDecimal.ZERO) == 0) || (safe.getInitialFunds().compareTo(account.getAvailableAmount()) > 0)) {
            throw new NotEnoughFundsException(NOT_ENOUGH_FUNDS_MESSAGE);
        }

        account.getSafes().add(safe);
        final BigDecimal reduceAvailableAmount = account.getAvailableAmount().subtract(safe.getInitialFunds());
        account.setAvailableAmount(reduceAvailableAmount);


        accountRepository.save(account);

        return safe.getId();
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
            final BigDecimal newFunds = yearsDifference.multiply(initialFunds).multiply(BigDecimal.valueOf(0.5));
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
}
