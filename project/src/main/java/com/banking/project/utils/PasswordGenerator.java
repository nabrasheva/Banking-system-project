package com.banking.project.utils;

import java.util.Random;

import static com.banking.project.constant.ExceptionMessages.NON_INSTANTIABLE_CLASS_MESSAGE;

public class PasswordGenerator {
    private PasswordGenerator(){
        throw new IllegalStateException(NON_INSTANTIABLE_CLASS_MESSAGE);
    }

    public static String generateRandomPassword() {
        final Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            password.append(digit);
        }

        return password.toString();
    }
}
