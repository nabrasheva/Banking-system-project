package com.banking.project.constant;

import static com.banking.project.constant.ExceptionMessages.NON_INSTANTIABLE_CLASS_MESSAGE;

public class SecurityAuthList {

    private SecurityAuthList(){
        throw new IllegalStateException(NON_INSTANTIABLE_CLASS_MESSAGE);
    }
    public static final String[] AUTH_LIST = {
            "/user/login",
            "/user/registration",
            "/user/recover"
    };

    public static final String LOGOUT_URL = "/logout";

    public static final String[] ADMIN_LIST = {
            "/user",
            "/user/account",
            "/account/{iban}/**"
    };

    public static final String[] USER_LIST = {
            "/user/**",
            "/account/**",
            "/safe"
    };
}
