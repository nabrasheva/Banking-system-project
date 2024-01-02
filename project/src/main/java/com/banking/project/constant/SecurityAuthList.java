package com.banking.project.constant;

public class SecurityAuthList {
    public static final String[] AUTH_LIST = {
            "/user/login",
            "/user/registration"
    };

    public static final String LOGOUT_URL = "/logout";

    public static final String[] ADMIN_LIST = {
            "/user\\?email=",
            "/user/account\\?email=",
            "/account/{iban}/**"
    };

    public static final String[] USER_LIST = {
            "/user/**",
            "/account/**"
    };
}
