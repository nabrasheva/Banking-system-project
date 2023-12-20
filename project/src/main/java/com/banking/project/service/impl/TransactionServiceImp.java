package com.banking.project.service.impl;

import com.banking.project.repository.TransactionRepository;
import com.banking.project.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {
    private final TransactionRepository transactionRepository;

}
