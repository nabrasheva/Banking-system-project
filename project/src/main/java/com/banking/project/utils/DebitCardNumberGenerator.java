package com.banking.project.utils;

import java.util.Random;

public class DebitCardNumberGenerator {
    private DebitCardNumberGenerator()
    {
        throw new IllegalStateException("Cannot instantiate DebitCardNumberGenerator");
    }
    public static String generateDebitCardNumber() {
        final Random random = new Random();

        // Generate a 16-digit random card number (for testing purposes)
        final StringBuilder cardNumber = new StringBuilder("4");  // Starting with the standard '4' for Visa
        for (int i = 1; i < 16; i++) {
            cardNumber.append(random.nextInt(10));  // Append random digits
        }

        return cardNumber.toString();
    }
}

