--Таблица двигатели:
create table engine(
id serial primary key,
manufacturer character varying(100),
type character varying(100),
volume real,
number_of_celinder integer
)
--Таблица КПП:
create table transmission(
id serial primary key,
manufacturer character varying(100),
type character varying(100),
number_of_gears integer
)
Таблица кузовов:
create table body(
id serial primary key,
manufacturer character varying(100),
type character varying(100),
number_of_doors integer
)
Таблица автомобилей:
create table car(
id serial primary key,
mark character varying(100),
model character varying(100),
color character varying(100),
id_body integer references body(id),
id_engine integer references engine(id),
id_transmission integer references transmission(id)
)
--Заполнение таблиц:
insert into engine(manufacturer, type, volume, number_of_celinder) values('LADA', 'Рядный', 1.6, 4);
insert into engine(manufacturer, type, volume, number_of_celinder) values('УАЗ', 'Рядный', 2.7, 4);
insert into engine(manufacturer, type, volume, number_of_celinder) values('TOYOTA', 'Рядный', 1.6, 4);
insert into transmission(manufacturer, type, number_of_gears) values('LADA', 'Механическая', 5);
insert into transmission(manufacturer, type, number_of_gears) values('УАЗ', 'Механическая', 5);
insert into transmission(manufacturer, type, number_of_gears) values('TOYOTA', 'Механическая', 5);
insert into body(manufacturer, type, number_of_doors) values('LADA', 'Седан', 4);
insert into body(manufacturer, type, number_of_doors) values('УАЗ', 'Микроавтобус', 5);
insert into body(manufacturer, type, number_of_doors) values('TOYOTA', 'Седан', 4);
insert into car(mark, model, color, id_body, id_engine, id_transmission) values('LADA', 'GRANTA', 'ТЕМНО-ЗЕЛЕНЫЙ', 1, 1, 1);
insert into car(mark, model, color, id_body, id_engine, id_transmission) values('УАЗ', 'БУХАНКА', 'СЕРЫЙ', 2, 2, 2);
insert into car(mark, model, color, id_body, id_engine, id_transmission) values('TOYOTA', 'COROLLA', 'СЕРЕБРИСТЫЙ', 3, 3, 3);
--Список всех машин и все привязанные к ним детали:
select c.id,
       c.mark,
       c.model,
       c.color,
       b.type as body_type,
       b.number_of_doors as number_of_doors,
       e.type as type_engine,
       e.volume as volume_engine,
       e.number_of_celinder as number_of_celinder_engine,
       t.type as transmission_type,
       t.number_of_gears as number_of_gears from car c
inner join engine e on c.id_engine = e.id
inner join transmission t on c.id_transmission = t.id
inner join body b on c.id_body = b.id

--Детали, которые не используются в машине:
--Кузова:
select b.manufacturer,
       b.type,
       b.number_of_doors from body b
left outer join car as c on c.id_body = b.id
where c.id is null;
--Двигатели:
select e.manufacturer,
       e.type,
       e.volume,
       e.number_of_celinder from engine e
left outer join car as c on c.id_engine = e.id
where c.id is null;
--Коробки передач:
select t.manufacturer,
       t.type,
       t.number_of_gears from transmission t
left outer join car as c on c.id_transmission = t.id
where c.id is null;