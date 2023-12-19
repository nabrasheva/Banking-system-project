package com.banking.project.utils;

import java.math.BigInteger;
import java.util.Random;

public class IbanGenerator {
    private IbanGenerator()
    {
        throw new IllegalStateException("Cannot instantiate IbanGenerator");
    }
    public static String generateIban(final String country) {
        final int bbanLength = 20;

        final String bban = generateRandomNumericString(bbanLength);

        final String checksum = calculateChecksum(country + "00" + bban);

        final String iban = country + checksum + bban;

        return iban;
    }

    private static String generateRandomNumericString(final int length) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static String calculateChecksum(String input) {
        input = input.toUpperCase();
        final StringBuilder numericInput = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            final char ch = input.charAt(i);
            if (Character.isLetter(ch)) {
                numericInput.append(ch - 'A' + 10);
            } else {
                numericInput.append(ch);
            }
        }

        // Use BigInteger to handle large numbers
        final BigInteger numericValue = new BigInteger(numericInput.toString());

        // Perform modulo 97 and subtract from 98
        final int checksumValue = 98 - (numericValue.mod(BigInteger.valueOf(97))).intValue();
        return String.format("%02d", checksumValue);
    }
}
