package com.banking.project.service;

import com.banking.project.dto.AccountDto;
import com.banking.project.dto.LoanDto;
import com.banking.project.dto.DebitCardDto;
import com.banking.project.dto.SafeDto;
import com.banking.project.dto.TransactionDto;
import com.banking.project.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    boolean doesIbanExist(String iban);

    Long saveAccount(Account account);

    SafeDto createSafeForAccount(String iban, SafeDto safeDto);

    void deleteSafeByNameAndIban(String name, String iban);

    List<TransactionDto> sendMoney(String senderIban, TransactionDto transactionDto);

    List<TransactionDto> takeLoan(LoanDto loanDto);

    List<TransactionDto> returnLoan(LoanDto loanDto);

    AccountDto getAccountByIban(String iban);

    List<TransactionDto> getAccountTransactions(String iban);

    List<SafeDto> getAccountSafes(String iban);

    DebitCardDto getDebitCardByIban(String iban);

    void updateSafe(String iban, String name, BigDecimal funds);
}
