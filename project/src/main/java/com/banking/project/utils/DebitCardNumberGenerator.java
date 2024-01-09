package com.banking.project.utils;

import java.util.Random;

public class DebitCardNumberGenerator {
    private DebitCardNumberGenerator()
    {
        throw new IllegalStateException("Cannot instantiate DebitCardNumberGenerator");
    }
    public static String generateDebitCardNumber() {
        final Random random = new Random();

        final StringBuilder cardNumber = new StringBuilder("4");
        for (int i = 1; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }

        return cardNumber.toString();
    }
}

