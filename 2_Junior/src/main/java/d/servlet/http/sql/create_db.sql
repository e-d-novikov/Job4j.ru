CREATE TABLE IF NOT EXISTS users(
id serial primary key,
login character varying (200),
password character varying (200),
role character varying (20),
user_name character varying (200),
user_sername character varying (200),
email character varying (200));

INSERT INTO users(login, password, role, user_name, user_sername, email)
VALUES('admin', 'admin', 'admin', 'Egor', 'Novikov', 'enovikovdev@gmail.com');
