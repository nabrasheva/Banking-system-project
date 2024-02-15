package com.banking.project.constant;

public class ExceptionMessages {

    /**
     * Not found messages
     */

    public static final String SAFE_NOT_FOUND_MESSAGE = "Safe was not found in the database!";
    public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account with this iban was not found in the database!";
    public static final String TRANSACTION_NOT_FOUND_MESSAGE = "Transaction was not found in the database";
    public static final String USER_NOT_FOUND_MESSAGE = "User was not found in the database";


    /**
     * Existing entity properties messages
     */

    public static final String SAFE_ALREADY_EXISTS_MESSAGE = "Safe with this name already exists in the database!";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "User with this email already exists in the database!";


    /**
     * Message for constructor in non-instantiable classes
     */

    public static final String NON_INSTANTIABLE_CLASS_MESSAGE = "Do not instantiate this class!";


    /**
     * Validation messages
     */


    public static final String NOT_ENOUGH_FUNDS_MESSAGE = "Unable to create safe: Not enough funds!";
    public static final String NON_ENOUGH_AMOUNT_MESSAGE = "Unable to create transaction: Not enough amount!";
    public static final String WRONG_CREDIT_AMOUNT_INPUT_MESSAGE = "Unable to return credit: Wrong credit amount!";


    /**
     * Global exception messages
     */

    public static final String CAUGHT_EXCEPTION = "An exception has been caught!";

    private ExceptionMessages() {
        throw new IllegalStateException(NON_INSTANTIABLE_CLASS_MESSAGE);
    }
}
