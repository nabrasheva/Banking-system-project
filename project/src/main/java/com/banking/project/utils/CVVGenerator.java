package com.banking.project.utils;

import java.util.Random;

import static com.banking.project.constant.ExceptionMessages.NON_INSTANTIABLE_CLASS_MESSAGE;

public class CVVGenerator {

    private CVVGenerator()
    {
        throw new IllegalStateException(NON_INSTANTIABLE_CLASS_MESSAGE);
    }
    public static long generateCVV()
    {
        final Random random = new Random();
        final long cvv = 100 + random.nextInt(900);
        return cvv;
    }
}
