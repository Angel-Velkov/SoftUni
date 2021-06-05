DROP SCHEMA IF EXISTS `movies`;

CREATE DATABASE `movies`;
USE `movies`;

CREATE TABLE `directors` (
	`id` INT PRIMARY KEY,
    `director_name` VARCHAR(50) NOT NULL,
    `notes` TEXT
);

INSERT INTO `directors`
VALUES
(1, 'Director_1', 'Az sum direktor'),
(2, 'Director_2', 'Az sum direktor'),
(3, 'Director_3', 'Az sum direktor'),
(4, 'Director_4', 'Az sum direktor'),
(5, 'Director_5', 'Az sum direktor')
;

CREATE TABLE `genres` (
	`id` INT PRIMARY KEY,
	`genre_name` VARCHAR(50) NOT NULL,
    `notes` TEXT
);

INSERT INTO `genres`
VALUES
(1, 'Genre_1', NULL),
(2, 'Genre_2', NULL),
(3, 'Genre_3', NULL),
(4, 'Genre_4', NULL),
(5, 'Genre_5', NULL)
;

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY,
	`category_name` VARCHAR(50) NOT NULL,
	`notes` TEXT
);

INSERT INTO `categories`
VALUES
(1, 'Category_1', NULL),
(2, 'Category_2', NULL),
(3, 'Category_3', NULL),
(4, 'Category_4', NULL),
(5, 'Category_5', NULL)
;

CREATE TABLE `movies` (
	`id` INT PRIMARY KEY,
    `title` VARCHAR(30) NOT NULL,
	`director_id` INT,
    `copyright_year` YEAR,
    `length` TIME,
    `genre_id` INT,
    `category_id` INT,
    `rating` INT,
    `notes` TEXT
);

INSERT INTO `movies`
VALUES
(1, 'Titanic', 2, 1997, '2:19:00', 4, 2, 97, NULL),
(2, 'Ti gonish', 1, 2016, '2:19:00', 3, 1, 100, NULL),
(3, 'Ted 2', 4, 2001, '2:19:00', 1, 3, 69, NULL),
(4, 'Jivotut mi', 3, 2021, '0:00:01', 5, 5, 0, NULL),
(5, 'CyberPunk 2077', 5, 2077, '10:10:10', 2, 4, 77, NULL)
;