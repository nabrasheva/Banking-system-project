package com.banking.project.service.impl;

import com.banking.project.repository.AccountRepository;
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
}
