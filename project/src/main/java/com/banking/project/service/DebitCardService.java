package com.banking.project.service;

import com.banking.project.entity.DebitCard;

public interface DebitCardService {
    boolean doesNumberExist(String number);

    Long saveDebitCard(DebitCard debitCard);
}
