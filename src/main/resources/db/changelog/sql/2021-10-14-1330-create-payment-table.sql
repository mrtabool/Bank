CREATE TABLE IF NOT EXISTS public.payment
(
    payment_id bigint NOT NULL,
    payer bigint NOT NULL,
    source bigint NOT NULL,
    recipient bigint NOT NULL,
    destination bigint NOT NULL,
    created TIMESTAMP,
    amount real,
    reason character varying(255) COLLATE pg_catalog."default",
    status character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT payment_pkey PRIMARY KEY (payment_id),
    CONSTRAINT payment_payer_client_fkey FOREIGN KEY (payer)
                REFERENCES public.client (client_id)
                ON UPDATE CASCADE
                ON DELETE RESTRICT,
    CONSTRAINT payment_source_account_fkey FOREIGN KEY (source)
                REFERENCES public.account (account_id)
                ON UPDATE CASCADE
                ON DELETE RESTRICT,
    CONSTRAINT payment_recipient_client_fkey FOREIGN KEY (recipient)
                REFERENCES public.client (client_id)
                ON UPDATE CASCADE
                ON DELETE RESTRICT,
    CONSTRAINT payment_destination_account_fkey FOREIGN KEY (destination)
                REFERENCES public.account (account_id)
                ON UPDATE CASCADE
                ON DELETE RESTRICT
)
GO

ALTER TABLE public.account
    OWNER to postgres
GO