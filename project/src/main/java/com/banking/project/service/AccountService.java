package com.banking.project.service;

import com.banking.project.dto.AccountDto;
import com.banking.project.dto.SafeDto;
import com.banking.project.dto.TransactionDto;
import com.banking.project.entity.Account;

import java.util.List;

public interface AccountService {

    boolean doesIbanExist(String iban);

    Long saveAccount(Account account);

    Long createSafeForAccount(Long accountId, SafeDto safeDto);

    void deleteSafeByNameAndIban(String name, String iban);

    AccountDto getAccountByIban(String iban);

    List<TransactionDto> getAccountTransactions(String iban);

    List<SafeDto> getAccountSafes(String iban);
}
