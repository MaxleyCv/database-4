DROP SCHEMA IF EXISTS leszczynski;
CREATE SCHEMA IF NOT EXISTS leszczynski;
USE leszczynski;

DROP TABLE IF EXISTS film_fact;
DROP TABLE IF EXISTS film_has_actor;
DROP TABLE IF EXISTS actor_fact;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS director;
DROP TABLE IF EXISTS income;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS film;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS user_security;
DROP TABLE IF EXISTS user;

-- actor

CREATE TABLE actor (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  appendix VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (id));

-- actor_fact

CREATE TABLE actor_fact (
  id INT NOT NULL AUTO_INCREMENT,
  fact LONGTEXT NULL DEFAULT NULL,
  actor_id INT NOT NULL,
  PRIMARY KEY (id),

  CONSTRAINT `fk_actor_fact_actor`
    FOREIGN KEY (actor_id)
    REFERENCES actor (id));

CREATE INDEX fk_actor_fact_actor_idx ON actor_fact (actor_id);

-- country

CREATE TABLE country (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  president VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (id));

CREATE UNIQUE INDEX country_name_idx ON country (name);

-- director

CREATE TABLE director (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL DEFAULT NULL,
  surname VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (id));

-- film

CREATE TABLE film (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) NULL DEFAULT NULL,
  description LONGTEXT NULL DEFAULT NULL,
  publish_year INT NULL DEFAULT NULL,
  country_id INT NULL DEFAULT NULL,
  director_id INT NOT NULL,
  PRIMARY KEY (id),

  CONSTRAINT `fk_film_director`
    FOREIGN KEY (director_id)
    REFERENCES director (id),
  CONSTRAINT `fk_film_country`
    FOREIGN KEY (country_id)
    REFERENCES country(id));

CREATE INDEX fk_film_director_idx ON film (director_id);

-- film_fact

CREATE TABLE film_fact (
  id INT NOT NULL AUTO_INCREMENT,
  fact LONGTEXT NULL DEFAULT NULL,
  film_id INT NOT NULL,
  PRIMARY KEY (id, film_id),

  CONSTRAINT `fk_film_fact_film`
    FOREIGN KEY (film_id)
    REFERENCES film (id));

CREATE INDEX fk_film_fact_film_idx ON film_fact (film_id);

-- income

CREATE TABLE income (
  id INT NOT NULL AUTO_INCREMENT,
  value_in_EUR VARCHAR(45) NULL DEFAULT NULL,
  film_id INT NOT NULL,
  country_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id, country_name),

  CONSTRAINT `fk_income_film`
    FOREIGN KEY (film_id)
    REFERENCES film (id),
  CONSTRAINT `fk_income_country`
    FOREIGN KEY (country_name)
    REFERENCES country (name));

CREATE INDEX fk_income_country_idx ON income (country_name);

-- user

CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) UNIQUE NOT NULL,
  name VARCHAR(45) NULL DEFAULT NULL,
  birth_date DATE NULL DEFAULT NULL,
  country_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),

  CONSTRAINT `fk_user_country`
    FOREIGN KEY (country_name)
    REFERENCES country (name));

CREATE INDEX fk_user_country_idx ON user (country_name);

CREATE UNIQUE INDEX username_UNIQUE ON user (username);

-- review

CREATE TABLE review (
  id INT NOT NULL AUTO_INCREMENT,
  points INT NULL DEFAULT NULL,
  text_of_review LONGTEXT NULL DEFAULT NULL,
  film_id INT NOT NULL,
  user_id INT NOT NULL,

  PRIMARY KEY (id),
  CONSTRAINT `fk_review_film`
    FOREIGN KEY (film_id)
    REFERENCES film (id));


CREATE INDEX fk_review_film_idx ON review (film_id);

-- user_secuirty

CREATE TABLE user_security (
  user_id INT NOT NULL,
  user_password VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_id),

  CONSTRAINT `fk_user_security_user`
    FOREIGN KEY (user_id)
    REFERENCES user (id));

CREATE INDEX fk_user_security_user_idx ON user_security (user_id);

-- film_has_actor

CREATE TABLE film_has_actor (
  film_id INT NOT NULL,
  actor_id INT NOT NULL,
  PRIMARY KEY (film_id, actor_id),

  CONSTRAINT `fk_film_has_actor_film`
    FOREIGN KEY (film_id)
    REFERENCES film (id),
  CONSTRAINT `fk_film_has_actor_actor`
    FOREIGN KEY (actor_id)
    REFERENCES actor (id));

CREATE INDEX fk_film_has_actor_actor_idx ON film_has_actor (actor_id);
CREATE INDEX fk_film_has_actor_film_idx ON film_has_actor (film_id);

INSERT INTO  actor(id, name, surname, appendix) VALUES
(1, 'MAX', 'BROWN', 'JR'),
(2, 'MAX', 'BROWN', NULL),
(3, 'MAX', 'BROWN', NULL),
(4, 'MAX', 'BROWN', NULL),
(5, 'MAX', 'BROWN', 'MR'),
(6, 'MAX', 'BROWN', NULL),
(7, 'MAX', 'BROWN', NULL),
(8, 'MAX', 'BROWN', NULL),
(9, 'MAX', 'BROWN', NULL),
(10, 'ANNIE', 'BROWN', 'MRS'),
(11, 'ANNIE', 'WESTIE', NULL);

INSERT INTO actor_fact
(id, fact, actor_id) VALUES
(1, 'gay', 2),
(2, 'heya', 1),
(3, 'gay', 1),
(4, 'heya', 11),
(5, 'gay', 1),
(6, 'heya', 2),
(7, 'gay', 1),
(8, 'heya', 3),
(9, 'gay', 8),
(10, 'heya', 1);

INSERT INTO country (name, president) VALUES
('AUSTRALIA', 'DODON'),
('CANADA', 'DODON'),
('GERMANY', 'DODON'),
('MOLDOVA', 'DODON'),
('NEW GUINEA', 'DODON'),
('OCEANIA', 'DODON'),
('POLAND', 'DODON'),
('TOGO', 'DODON'),
('UKRAINE', 'DODON'),
('USA', 'DODON');

INSERT INTO director (id, name, surname) VALUES

(1, 'Igor', 'Cuciuc'),
(2, 'Ion', 'Popescu'),
(3, 'Ionel', 'Onciul'),
(4, 'Rafal', 'Gabriescu'),
(5, 'Aurel', 'Chipianu'),
(6, 'Igor', 'Cuciuc'),
(7, 'Ion', 'Popescu'),
(8, 'Ionel', 'Onciul'),
(9, 'Rafal', 'Gabriescu'),
(10, 'Aurel', 'Chipianu');


INSERT INTO film (id, title, description, publish_year, country_id, director_id) VALUES
(6, 'STEUA FARA NUME', 'Filmul mai bun', 1968, 2, 1),
(7, 'W CIEMNOSCI', 'To jest fajny filmik', 2001, 3, 3),
(8, 'STEUA FARA NUME', 'Filmul mai bun', 1968, 6, 1),
(9, 'W CIEMNOSCI', 'To jest fajny filmik', 2001, 7, 3),
(10, 'STEUA FARA NUME', 'Filmul mai bun', 1968, 7, 1),
(11, 'W CIEMNOSCI', 'To jest fajny filmik', 2001, 7, 3),
(12, 'STEUA FARA NUME', 'Filmul mai bun', 1968, 7, 1),
(13, 'W CIEMNOSCI', 'To jest fajny filmik', 2001, 8, 3),
(14, 'STEUA FARA NUME', 'Filmul mai bun', 1968, 7, 1),
(15, 'W CIEMNOSCI', 'To jest fajny filmik', 2001, 4, 3);

INSERT INTO film_fact (id, film_id, fact) VALUES
(11, 11, 'Filmul e mai asteapta pentru copiii'),
(12, 14, 'Ten film jest dobry na dzieci'),
(13, 11, 'Filmul e mai asteapta pentru copiii'),
(14, 14, 'Ten film jest dobry na dzieci'),
(15, 11, 'Filmul e mai asteapta pentru copiii'),
(6, 14, 'Ten film jest dobry na dzieci'),
(7, 11, 'Filmul e mai asteapta pentru copiii'),
(8, 14, 'Ten film jest dobry na dzieci'),
(9, 11, 'Filmul e mai asteapta pentru copiii'),
(10, 14, 'Ten film jest dobry na dzieci');

INSERT INTO film_has_actor
(film_id, actor_id) VALUES
(11, 2),
(12, 4),
(10, 4),
(13, 5),
(14, 6),
(6, 2),
(6, 3),
(7, 1),
(9, 2),
(10, 2);


INSERT INTO  income 
(country_name, value_in_EUR, film_id) VALUES
('MOLDOVA', 100, 10),
('MOLDOVA', 100, 11),
('MOLDOVA', 100, 12),
('MOLDOVA', 100, 13),
('MOLDOVA', 100, 14),
('POLAND', 100, 15),
('POLAND', 100, 6),
('POLAND', 100, 7),
('POLAND', 100, 8),
('POLAND', 100, 9);


INSERT INTO  user (username, name, birth_date, country_name) VALUES
('felixor1',' Felix', '2020-10-20', 'USA'),
('felixor2', 'Felix', '2020-10-20', 'USA'),
('felixor3', 'Felix','2020-10-20', 'USA'),
('felixor4', 'Felix', '2020-10-20', 'USA'),
('fima2', 'EFIM', '1980-12-30', 'POLAND'),
('florea_conca', 'Fima', '2010-12-19', 'MOLDOVA'),
('florea_conca1',' Fima', '2010-12-19', 'MOLDOVA'),
('florea_conca2', 'Fima', '2010-12-19', 'MOLDOVA'),
('florea_conca3', 'Fima', '2010-12-19', 'MOLDOVA'),
('roma1', 'Roman', '2031-10-20', 'UKRAINE');

INSERT INTO  review (id, points, text_of_review, film_id, user_id) VALUES
(1, 5, 'PENTRU COPIII', 11, 2),
(2, 2, 'GOWNO', 12, 4),
(3, 5, 'PENTRU COPIII', 13, 1),
(4, 2, 'GOWNO', 14, 2),
(5, 5, 'PENTRU COPIII', 15, 2),
(6, 2, 'GOWNO', 6, 2),
(7, 5, 'PENTRU COPIII', 7, 3),
(8, 2, 'GOWNO', 8, 2),
(9, 5, 'PENTRU COPIII', 9, 1),
(10, 2, 'GOWNO', 10, 3);

INSERT INTO user_security (user_id, user_password) VALUES
(1, 123123),
(2, 123123),
(3, 123123),
(4, 123123),
(5, 123123),
(6, 123123),
(7, 123123),
(8, 123123),
(9, 123123),
(10, 123123);
