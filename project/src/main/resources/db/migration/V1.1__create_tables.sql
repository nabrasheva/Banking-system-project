CREATE TABLE IF NOT EXISTS public.debit_card (
                                                 id BIGSERIAL PRIMARY KEY,
                                                 number VARCHAR(255) UNIQUE NOT NULL,
                                                 expiry_date DATE NOT NULL,
                                                 cvv BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.account (
                         id BIGSERIAL PRIMARY KEY,
                         iban VARCHAR(255) UNIQUE NOT NULL,
                         available_amount NUMERIC(19, 2) NOT NULL,
                         debit_card_id BIGINT,
                         FOREIGN KEY (debit_card_id) REFERENCES debit_card (id)
);

CREATE TABLE IF NOT EXISTS public.bank_user
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(255)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    country    VARCHAR(255)        NOT NULL,
    account_id BIGINT,
    FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS public.safe (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) UNIQUE NOT NULL,
                      key VARCHAR(255) NOT NULL,
                      initial_funds NUMERIC(19, 2) NOT NULL,
                      account_id BIGINT,
                      creation_date DATE           NOT NULL,
                      FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS public.transaction (
                             id BIGSERIAL PRIMARY KEY,
                             sent_amount NUMERIC(19, 2) NOT NULL,
                             issue_date TIMESTAMP NOT NULL,
                             receiver_iban VARCHAR(255),
                             reason VARCHAR(255) NOT NULL,
                             credit_payment BOOLEAN,
                             account_id BIGINT,
                             FOREIGN KEY (account_id) REFERENCES account(id)
);

