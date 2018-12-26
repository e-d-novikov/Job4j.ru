--создание базы данных
create database tasks;
--создание таблиц
create table law(
id serial primary key,
law character varying(100)
)

create table role(
id serial primary key,
role character varying(100)
)

create table roles(
id serial primary key,
role_id integer references role(id),
law_id integer references law(id)
)

create table users(
id serial primary key,
user_name character varying(100),
role_id integer references roles(id)
)

create table category(
id serial primary key,
category character varying(100)
)

create table state(
id serial primary key,
state character varying(100)
)

create table items(
id serial primary key,
item character varying(2000),
id_user integer references users(id),
id_category integer references category(id),
id_state integer references state(id)
)

create table comments(
id serial primary key,
comment character varying(id),
id_item integer references items(id)
)

create table attach(
id serial primary key,
attach text,
id_item integer references items(id)
)
--заполнение таблиц

--law
insert into law(law) values('creature');
insert into law(law) values('editing');
insert into law(law) values('view');
insert into law(law) values('delete');

--role
insert into role(role) values('administrator');
insert into role(role) values('user');
insert into role(role) values('guest');

--roles
--admin
insert into roles(role_id, law_id) values(1, 1);
insert into roles(role_id, law_id) values(1, 2);
insert into roles(role_id, law_id) values(1, 3);
insert into roles(role_id, law_id) values(1, 4);
--user
insert into roles(role_id, law_id) values(2, 1);
insert into roles(role_id, law_id) values(2, 2);
insert into roles(role_id, law_id) values(2, 3);
--guest
insert into roles(role_id, law_id) values(3, 3);

--category
insert into category(category) values('high');
insert into category(category) values('middle');
insert into category(category) values('low');

--state
insert into state(state) values('created');
insert into state(state) values('under');
insert into state(state) values('executed');