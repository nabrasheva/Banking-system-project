package com.banking.project.service;

import com.banking.project.dto.*;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

public interface BankUserService {
    void registration(final BankUserDto bankUserDto) throws MailjetSocketTimeoutException, MailjetException;

    void deleteBankUser(String email);

    BankUserDto getUserByEmail(String email);

    void updateUsernameAndPassword(String email, UpdateBankUserDto bankUser);

    LoginResponse login(LoginRequest loginRequest);

    AccountDto getAccountByEmail(String email);

    void createAdmin(BankUserDto bankUserDto);

}
