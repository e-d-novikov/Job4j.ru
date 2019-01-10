create table accounts (
id serial primary key,
name text,
phone text)

create table hall (
id serial primary key,
row integer,
place integer,
price integer,
id_account integer references accounts(id))