DROP SCHEMA IF EXISTS `movies`;

CREATE DATABASE `movies`;

USE `movies`;

CREATE TABLE `directors` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `director_name` VARCHAR(50) NOT NULL,
    `notes` TEXT
);

INSERT INTO `directors` (`director_name`, `notes`)
VALUES
('Director_1', 'Az sum direktor'),
('Director_2', 'Az sum direktor'),
('Director_3', 'Az sum direktor'),
('Director_4', 'Az sum direktor'),
('Director_5', 'Az sum direktor')
;

CREATE TABLE `genres` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`genre_name` VARCHAR(50) NOT NULL,
    `notes` TEXT
);

INSERT INTO `genres` (`genre_name`, `notes`)
VALUES
('Genre_1', NULL),
('Genre_2', NULL),
('Genre_3', NULL),
('Genre_4', NULL),
('Genre_5', NULL)
;

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`category_name` VARCHAR(50) NOT NULL,
	`notes` TEXT
);

INSERT INTO `categories` (`category_name`, `notes`)
VALUES
('Category_1', NULL),
('Category_2', NULL),
('Category_3', NULL),
('Category_1', NULL),
('Category_4', NULL),
('Category_5', NULL)
;

CREATE TABLE `movies` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(30) NOT NULL,
	`director_id` INT,
    `copyright_year` YEAR,
    `length` TIME,
    `genre_id` INT,
    `category_id` INT,
    `rating` INT,
    `notes` TEXT,
    
    CONSTRAINT `fk_movies_directors`
    FOREIGN KEY (`director_id`)
    REFERENCES `directors` (`id`),
    
    CONSTRAINT `fk_movies_genres`
    FOREIGN KEY (`genre_id`)
    REFERENCES `genres` (`id`),
    
    CONSTRAINT `fk_movies_categories`
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`)
);

INSERT INTO `movies` (`title`, `director_id`, `copyright_year`, `length`, `genre_id`, `category_id`, `rating`, `notes`)
VALUES
('Titanic', 2, 1997, '2:19:00', 4, 2, 97, NULL),
('Ti gonish', 1, 2016, '2:19:00', 3, 1, 100, NULL),
('Ted 2', 4, 2001, '2:19:00', 1, 3, 69, NULL),
('Jivotut mi', 3, 2021, '0:00:01', 5, 5, 0, NULL),
('CyberPunk 2077', 5, 2077, '10:10:10', 2, 4, 77, NULL)
;