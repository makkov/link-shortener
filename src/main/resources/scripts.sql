create table links
(
    id       integer not null
        constraint links_pk
            primary key,
    original text,
    shorted  text
);

create sequence link_sequence;

CREATE PROCEDURE insert_random()
    LANGUAGE SQL AS
$$
INSERT INTO links(id, shorted, original)
VALUES (nextval('link_sequence'), '1', '2');
$$;

CREATE PROCEDURE get_сount_link_like(IN param TEXT, OUT count INT)
    LANGUAGE SQL AS
$$
SELECT COUNT(*)
FROM links
WHERE original like param
    $$

create index links_shorted_idx on links(shorted);