package com.banking.project.service.impl;

import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Account;
import com.banking.project.entity.Safe;
import com.banking.project.repository.AccountRepository;
import com.banking.project.repository.specification.AccountSpecification;
import com.banking.project.service.AccountService;
import com.banking.project.service.DebitCardService;
import com.banking.project.service.SafeService;
import com.banking.project.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        final Safe safe = Safe.builder().name(safeDto.getName()).key(safeDto.getKey()).initialFunds(safeDto.getInitialFunds()).build();

        account.getSafes().add(safe);

        accountRepository.save(account);

        return safe.getId();
    }
}
