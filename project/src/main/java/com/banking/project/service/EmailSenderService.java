package com.banking.project.service;

import com.banking.project.entity.BankUser;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

public interface EmailSenderService {
    void sendRegistrationConfirmationEmail(BankUser user) throws MailjetSocketTimeoutException,MailjetException;
}
