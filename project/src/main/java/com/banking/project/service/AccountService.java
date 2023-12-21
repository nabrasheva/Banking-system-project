package com.banking.project.service;

import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Account;

import java.math.BigDecimal;

public interface AccountService {

    boolean doesIbanExist(String iban);

    Long saveAccount(Account account);

    Long createSafeForAccount(Long accountId, SafeDto safeDto);

    void deleteSafeByNameAndIban(String name, String iban);

    void sendMoney(String senderIban, String receiverIban, BigDecimal amount, String reason);

    void takeLoan(String iban, BigDecimal amount);

    void returnLoan(String iban, BigDecimal amount);
}
