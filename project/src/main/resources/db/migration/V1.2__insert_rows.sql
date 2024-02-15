INSERT INTO public.debit_card (number, expiry_date, cvv)
VALUES ('1234567890123456', '2025-12-31', 123),
       ('9876543210987654', '2024-12-31', 456);

INSERT INTO public.account (iban, available_amount, credit_amount, debit_card_id)
VALUES ('IBAN123456789', 1000.00, 100, 1),
       ('IBAN987654321', 847.50, 101.00, 2);

INSERT INTO public.bank_user (email, username, password, country, role, account_id)
VALUES ('user1@gmail.com', 'user1', '$2a$10$2sZZVIg5Zno9sG6SqQ4OPuSwDJr6hTnsErnrKTSOkDyyIDu7sfiea', 'BG', 'USER',
        1),
       ('admin@gmail.com', 'admin', '$2a$10$aSHo/fBPG4vNdilwJwqqVeCSSc644QNT81N644rCqs0NfLK2NgMla', 'FR',
        'ADMIN', null),
       ('user2@gmail.com', 'user2', '$2a$10$zYh.At7anoikUklOuSDLp.Pvqy2ALKW.skdwKYJVpPaza69XlvA/m', 'GB', 'USER',
        2);

INSERT INTO public.safe (name, key, initial_funds, account_id, creation_date)
VALUES ('Safe1', 'Key1', 100.00, 1, '2023-12-01'),
       ('Safe2', 'Key2', 50.50, 2, '2023-12-01'),
       ('SafeN', 'KeyN', 99.99, 2, '2023-12-01');

INSERT INTO public.transaction (sent_amount, issue_date, receiver_iban, reason, account_id,
                                credit_payment)
VALUES (50.00, '2023-01-01', 'ReceiverIBAN1', 'Payment for Service 1', 1, false),
       (75.50, '2023-02-01', 'ReceiverIBAN2', 'Payment for Service 2', 2, false),
       (99.99, '2023-03-01', 'ReceiverIBANN', 'Payment for Service N', 2, false);
