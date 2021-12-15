--DROP EXTENSION "uuid-ossp";
--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--DROP TABLE IF EXISTS app_user";

create table app_user
(
    id            uuid UNIQUE        NOT NULL DEFAULT uuid_generate_v4(),
    username      VARCHAR(32) UNIQUE NOT NULL CHECK (LENGTH(username) >= 2),
    password      VARCHAR(128)       NOT NULL CHECK (LENGTH(password) >= 7),
    account_type  VARCHAR(16)        NOT NULL DEFAULT 'BASIC' CHECK (account_type IN ('DEV', 'ADMIN', 'BASIC', 'BANNED')),
    creation_date DATE               NOT NULL DEFAULT CURRENT_DATE,
    last_updated  DATE               NOT NULL DEFAULT CURRENT_DATE
);

create table user_profile
(
    user_id      UUID PRIMARY KEY REFERENCES app_user (id) ON DELETE CASCADE,
    email        VARCHAR(128) UNIQUE NOT NULL DEFAULT '' CHECK (LENGTH(email) >= 6),
    first_name   VARCHAR(64)         NOT NULL DEFAULT '' CHECK (LENGTH(first_name) >= 2),
    last_name    VARCHAR(64)         NOT NULL DEFAULT '' CHECK (LENGTH(last_name) >= 2),
    last_updated DATE                NOT NULL DEFAULT CURRENT_DATE
);

create table card
(
    id          INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    name        VARCHAR(64) UNIQUE NOT NULL DEFAULT '',
    description VARCHAR(256)       NOT NULL DEFAULT '' CHECK (description <> ''),
    stars       INT                NOT NULL DEFAULT 0 CHECK (stars >= 0 and stars <= 5)
);


create table collection
(
    id      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    mgp     INT NOT NULL DEFAULT 100 CHECK (mgp >= 0 and mgp <= 9999),
    user_id UUID REFERENCES app_user (id) ON DELETE CASCADE
);

create table deck
(
    id            INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    collection_id INT REFERENCES collection (id) ON DELETE CASCADE
);

create table collection_card
(
    collection_id INT unique REFERENCES collection (id) ON DELETE CASCADE,
    card_id       INT unique REFERENCES card (id) ON DELETE CASCADE,

    CONSTRAINT collection_card_pk
        PRIMARY KEY (collection_id, card_id)
);
