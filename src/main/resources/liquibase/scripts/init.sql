-- liquibase formatted sql

-- changeset makkovv:1
create table links
(
    id       integer not null
        constraint links_pk
            primary key,
    original text,
    shorted  text,
    status text
);

create sequence link_sequence;