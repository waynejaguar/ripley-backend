CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username        VARCHAR(10) NOT NULL
    CONSTRAINT users_username_key
			UNIQUE,
    password   VARCHAR(256) NOT NULL,
    name varchar(100) default ''::character varying not null,
    second_name varchar(100) default ''::character varying not null,
    father_last_name varchar(100) default ''::character varying not null,
    mother_last_name varchar(100) default ''::character varying not null,
    email_user varchar(100) default ''::character varying not null,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);
