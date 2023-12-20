package com.banking.project.service;

import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Account;

public interface AccountService {

    boolean doesIbanExist(String iban);

    Long saveAccount(Account account);

    Long createSafeForAccount(Long accountId, SafeDto safeDto);

    void deleteSafeByNameAndIban(String name, String iban);
}
