package com.banking.project.utils;

import java.util.Random;

public class CVVGenerator {

    private CVVGenerator()
    {
        throw new IllegalStateException("Cannot instantiate CVVGenerator");
    }
    public static long generateCVV()
    {
        final Random random = new Random();
        final long cvv = 100 + random.nextInt(900);
        return cvv;
    }
}
