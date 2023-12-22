package com.banking.project.service;

import com.banking.project.dto.BankUserDto;
import com.banking.project.dto.UpdateBankUserDto;

public interface BankUserService {
    Long createBankUser(final BankUserDto bankUserDto);

    void deleteBankUser(String email);

    BankUserDto getUserByEmail(String email);

    void updateUsernameAndPassword(String email, UpdateBankUserDto bankUser);
}
