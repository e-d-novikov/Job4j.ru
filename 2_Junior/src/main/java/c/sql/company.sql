CREATE TABLE company(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id));

CREATE TABLE person(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id));

INSERT INTO company(id, name) VALUES(1, 'First Company');
INSERT INTO company(id, name) VALUES(2, 'Second Company');
INSERT INTO company(id, name) VALUES(3, 'Third Company');
INSERT INTO company(id, name) VALUES(4, 'Fourth Company');
INSERT INTO company(id, name) VALUES(5, 'Fifth Company');

INSERT INTO person(id, name, company_id) VALUES(1, 'First Person', 1);
INSERT INTO person(id, name, company_id) VALUES(2, 'Second Person', 1);
INSERT INTO person(id, name, company_id) VALUES(3, 'Third Person', 2);
INSERT INTO person(id, name, company_id) VALUES(4, 'Fourth Person', 2);
INSERT INTO person(id, name, company_id) VALUES(5, 'Fifth Person', 3);
INSERT INTO person(id, name, company_id) VALUES(6, 'Sixth Person', 3);
INSERT INTO person(id, name, company_id) VALUES(7, 'Seventh Person', 4);
INSERT INTO person(id, name, company_id) VALUES(8, 'Eighth Person', 4);
INSERT INTO person(id, name, company_id) VALUES(9, 'Ninth Person', 5);
INSERT INTO person(id, name, company_id) VALUES(10, 'Tenth Person', 5);
INSERT INTO person(id, name, company_id) VALUES(11, 'Eleventh Person', 5);

SELECT person.name, company.name FROM person
LEFT JOIN company ON person.company_id = company.id
WHERE person.company_id != '5';

CREATE VIEW all_persons as
SELECT person.name AS person, company.name AS company FROM person
LEFT JOIN company ON person.company_id = company.id;

CREATE VIEW count_persons as
select all_persons.company, count(*) as "count" from all_persons
group  by company order by company;

SELECT count_persons.company, count FROM count_persons WHERE count = (SELECT max(count) FROM count_persons);

