--Создание таблицы продукты:
create table product(
id serial primary key,
name character varying(100),
type_id integer references type_product(id),
expired_date date,
price integer
)
--Создание таблицы типов продуктов:
create table type_product(
id serial primary key,
type character varying(100)
)

--Наполнение таблиц:
insert into type_product(type) values('СЫР');
insert into type_product(type) values('МОЛОКО');
insert into type_product(type) values('МОРОЖЕННОЕ');
insert into product(name, type_id, expired_date, price) values('Сыр сливочный', 1, '2018-10-10 00:00:00', 120);
insert into product(name, type_id, expired_date, price) values('Сыр косичка', 1, '2018-10-20 00:00:00', 80);
insert into product(name, type_id, expired_date, price) values('Мороженное Пломбир', 3, '2018-10-15 00:00:00', 50);
insert into product(name, type_id, expired_date, price) values('Мороженное Шоколадная крошка', 3, '2018-10-15 00:00:00', 50);
insert into product(name, type_id, expired_date, price) values('Молоко 3,8%', 2, '2018-10-5 00:00:00', 70);
insert into product(name, type_id, expired_date, price) values('Молоко 2,5%', 2, '2018-10-5 00:00:00', 65);



--1. Запрос на получение всех продуктов с типом "СЫР":
select * from product as p
inner join type_product as t on p.type_id = t.id
where t.type = 'СЫР';
--2. Запрос на получение списка всех продуктов, в имени которого есть слово "мороженное":
select * from product where name like '%Мороженное%';
--3. Запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце:
select * from product where expired_date between '2018-10-01 00:00:00' and '2018-10-31 00:00:00';
--4. Запрос, который выводит самый дорогой продукт:
select * from product where price = (select max(price) from product);
--5. Запрос, который выводит количество всех продуктов определенного типа:
create view count_type as
select p.type_id, count(*) as "count" from product as p
inner join type_product as t on p.type_id = t.id
group  by type_id order by type_id;

select * from count_type as c
inner join type_product as t on c.type_id = t.id;
--6. Запрос на получение списка всех продуктов с типом "СЫР" и "МОЛОКО":
select * from product as p
inner join type_product as t on p.type_id = t.id
where t.type = 'СЫР'
or t.type = 'МОЛОКО';
--7. Запрос, который выводит тип продуктов, которых осталось меньше 10 штук:
create view count_type as
select p.type_id, count(*) as "count" from product as p
inner join type_product as t on p.type_id = t.id
group  by type_id order by type_id;

select type from count_type as c
inner join type_product as t on c.type_id = t.id
where count < 10;
--8. Вывести все продукты и их тип:
select name, type from product as p
inner join type_product as c on p.type_id = c.id;