CREATE TABLE accounts (
id serial primary key,
name character varying(100),
phone character varying(100));

INSERT INTO accounts (name, phone) VALUES ('admin', 'admin');

CREATE TABLE hall (
id serial primary key,
row integer,
place integer,
price integer,
id_account integer references accounts(id));

INSERT INTO hall (row, place, price, id_account) VALUES (1, 1, 200, 1);
INSERT INTO hall (row, place, price, id_account) VALUES (1, 2, 200, 1);
INSERT INTO hall (row, place, price, id_account) VALUES (1, 3, 200, 1);
INSERT INTO hall (row, place, price, id_account) VALUES (2, 1, 200, 1);
INSERT INTO hall (row, place, price, id_account) VALUES (2, 2, 200, 1);
INSERT INTO hall (row, place, price, id_account) VALUES (2, 3, 200, 1);
INSERT INTO hall (row, place, price, id_account) VALUES (3, 1, 250, 1);
INSERT INTO hall (row, place, price, id_account) VALUES (3, 2, 250, 1);
INSERT INTO hall (row, place, price, id_account) VALUES (3, 3, 250, 1);