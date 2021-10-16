CREATE TABLE IF NOT EXISTS public.client
(
    client_id bigint NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT client_pkey PRIMARY KEY (client_id)
)
GO

ALTER TABLE public.client
    OWNER to postgres
GO
