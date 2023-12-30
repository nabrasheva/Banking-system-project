package com.banking.project.service;

import com.banking.project.dto.BankUserDto;
import com.banking.project.dto.LoginRequest;
import com.banking.project.dto.LoginResponse;
import com.banking.project.dto.UpdateBankUserDto;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

public interface BankUserService {
    LoginResponse createBankUser(final BankUserDto bankUserDto) throws MailjetSocketTimeoutException, MailjetException;

    void deleteBankUser(String email);

    BankUserDto getUserByEmail(String email);

    void updateUsernameAndPassword(String email, UpdateBankUserDto bankUser);

    LoginResponse login(LoginRequest loginRequest);
}
