package com.banking.project.service.impl;

import com.banking.project.entity.BankUser;
import com.banking.project.service.EmailSenderService;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.banking.project.constant.EmailSenderConstant.SENDER_EMAIL;
import static com.banking.project.constant.EmailSenderConstant.SENDER_NAME;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final String apiKey;

    private final String secretKey;

    public EmailSenderServiceImpl(@Value("${api.key}") final String apiKey, @Value("${api.secret}") final String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    @Override
    public void sendRegistrationConfirmationEmail(final BankUser user, String iban, String number) throws MailjetSocketTimeoutException, MailjetException {
        final String recipientEmail = user.getEmail();
        final String recipientName = user.getUsername();
        String loginUrl = "http://localhost:4200/login";

        final MailjetClient client;
        final MailjetRequest request;

        client = new MailjetClient(apiKey, secretKey, new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", SENDER_EMAIL)
                                        .put("Name", SENDER_NAME))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", recipientEmail)
                                                .put("Name", recipientName)))
                                .put(Emailv31.Message.SUBJECT, "Your registration confirmation")
                                .put(Emailv31.Message.HTMLPART,
                                        "<h3>Dear " + recipientName +
                                                ",</h3><p>You have successfully registered. " + "</p>" +
                                                "<p>Your IBAN is: " + iban + "</p>" +
                                                "<p>Your debit card number is: " + number + "</p>" +
                                                "<p>You can use this <a href='" + loginUrl + "'>link to login</a>.</p>")
                        ));

        client.post(request);
    }
}
