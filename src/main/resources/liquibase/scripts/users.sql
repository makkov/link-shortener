-- liquibase formatted sql

-- changeset makkovv:2
create table users
(
    id       integer not null
        primary key,
    username text    not null,
    password text    not null,
    roles    text    not null
);

create sequence user_sequence;