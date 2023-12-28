INSERT INTO public.debit_card (number, expiry_date, cvv)
VALUES ('1234567890123456', '2023-12-31', 123),
       ('9876543210987654', '2024-12-31', 456),
       ('6543210987654321', '2025-12-31', 789);

INSERT INTO public.account (iban, available_amount, credit_amount, debit_card_id)
VALUES ('IBAN123456789', 1000.00, 100, 1),
       ('IBAN987654321', 500.50, null, 2),
       ('IBAN987654325', 999.99, 500, 3);

INSERT INTO public.bank_user (email, username, password, country, role, account_id)
VALUES ('email1', 'user1', 'password1', 'Country1', 'USER', 1),
       ('email2', 'user2', 'password2', 'Country2', 'ADMIN', 2),
       ('email3', 'userN', 'passwordN', 'CountryN', 'USER', 3);

INSERT INTO public.safe (name, key, initial_funds, account_id, creation_date)
VALUES ('Safe1', 'Key1', 100.00, 1, '2023-12-01'),
       ('Safe2', 'Key2', 50.50, 2, '2023-12-01'),
       ('SafeN', 'KeyN', 99.99, 3, '2023-12-01');

INSERT INTO public.transaction (sent_amount, issue_date, receiver_iban, reason, account_id,
                                credit_payment)
VALUES (50.00, '2023-01-01', 'ReceiverIBAN1', 'Payment for Service 1', 1, false),
       (75.50, '2023-02-01', 'ReceiverIBAN2', 'Payment for Service 2', 2, false),
       (99.99, '2023-03-01', 'ReceiverIBANN', 'Payment for Service N', 3, false);
