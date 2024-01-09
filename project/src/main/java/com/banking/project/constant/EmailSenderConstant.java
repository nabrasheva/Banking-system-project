package com.banking.project.constant;

import static com.banking.project.constant.ExceptionMessages.NON_INSTANTIABLE_CLASS_MESSAGE;

public class EmailSenderConstant {

    public static final String SENDER_EMAIL = "annargeorgieva21@gmail.com";
    public static final String SENDER_NAME = "Banking system";

    public  static final String LOGIN_URL = "http://localhost:4200/login";

    private EmailSenderConstant() {
        throw new IllegalStateException(NON_INSTANTIABLE_CLASS_MESSAGE);
    }
}
