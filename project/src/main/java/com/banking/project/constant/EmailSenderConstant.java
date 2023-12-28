package com.banking.project.constant;

import static com.banking.project.constant.ExceptionMessages.NON_INSTANTIABLE_CLASS_MESSAGE;

public class EmailSenderConstant {

    public static final String SENDER_EMAIL = "annargeorgieva21@gmail.com";
    public static final String SENDER_NAME = "Banking system";

    private EmailSenderConstant() throws IllegalAccessException {
        throw new IllegalAccessException(NON_INSTANTIABLE_CLASS_MESSAGE);
    }
}
