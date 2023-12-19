package com.banking.project.service;

import com.banking.project.entity.Account;

public interface AccountService {

    boolean doesIbanExist(String iban);

    Long saveAccount(Account account);
}
