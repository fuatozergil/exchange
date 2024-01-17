CREATE SCHEMA public AUTHORIZATION pg_database_owner;

CREATE TABLE public."transaction" (
	id int8 NOT NULL DEFAULT nextval('transaction_seq'::regclass),
	transaction_type varchar NULL,
	currency varchar NULL,
	target_currency varchar NULL,
	amount numeric NULL,
	conversion_amount numeric NULL,
	rate numeric NULL,
	"date" date NULL,
	CONSTRAINT id_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.transactions_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;