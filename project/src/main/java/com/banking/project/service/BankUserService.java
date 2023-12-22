package com.banking.project.service;

import com.banking.project.dto.BankUserDto;

public interface BankUserService {
    Long createBankUser(final BankUserDto bankUserDto);

    void deleteBankUser(String email);

    BankUserDto getUserByEmail(String email);
}
