CREATE TABLE IF NOT EXISTS public.account
(
    account_id bigint NOT NULL,
    account_num character varying(255) COLLATE pg_catalog."default",
    account_type character varying(255) COLLATE pg_catalog."default",
    balance real,
    client_id bigint NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (account_id),
    CONSTRAINT account_client_fkey FOREIGN KEY (client_id)
            REFERENCES public.client (client_id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)
GO

ALTER TABLE public.account
    OWNER to postgres
GO