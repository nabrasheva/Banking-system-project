package com.banking.project.service.impl;

import com.banking.project.repository.BankUserRepository;
import com.banking.project.service.AccountService;
import com.banking.project.service.BankUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankUserServiceImpl implements BankUserService {

    private final AccountService accountService;
    private final BankUserRepository bankUserRepository;
}
