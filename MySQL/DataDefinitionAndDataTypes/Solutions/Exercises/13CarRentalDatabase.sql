DROP SCHEMA IF EXISTS `car_rental`;

CREATE DATABASE `car_rental`;

USE `car_rental`;

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(30) NOT NULL,
    `daily_rate` DOUBLE DEFAULT 0.0,
    `weekly_rate` DOUBLE DEFAULT 0.0,
    `monthly_rate` DOUBLE DEFAULT 0.0,
    `weekend_rate` DOUBLE DEFAULT 0.0
);

INSERT INTO `categories` (`category`, `daily_rate`, `weekly_rate`, `monthly_rate`, `weekend_rate`)
VALUES
('van', 11.9, 12.9, 13.9, 14.9),
('sedan', 21.9, 22.9, 23.9, 24.9),
('bus', 32.9, 32.9, 33.9, 34.9),
('coupe', 41.9, 42.9, 43.9, 44.9),
('cuv', 51.9, 52.9, 53.9, 54.9)
;

CREATE TABLE `cars` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `plate_number` CHAR(6) NOT NULL,
    `make` DATE,
    `model` VARCHAR(30),
    `car_year` YEAR,
    `category_id` INT,
    `doors` INT,
    `picture` BLOB,
    `car_condition` VARCHAR(100),
	`available` TINYINT NOT NULL
);

INSERT INTO `cars` (`plate_number`, `make`, `model`, `car_year`, `category_id`, `doors`, `picture`, `car_condition`, `available`)
VALUES
('AZ0694', '2000-12-01', 'Ferrari', 1997, 1, 2, 'Horse', 'OK', true),
('RS0324', '2001-07-05', 'Opel', 1995, 2, 4, 'Landfill', 'RUSTY', true),
('SS6445', '1997-11-13', 'Ford', 1997, 3, 4, 'Crashed into a tree', 'BAD', false),
('BG8784', '1980-02-21', 'BMW', 1970, 4, 1, 'Servise', 'OK', true),
('DS5299', '2010-04-11', 'Subaru', 2000, 5, 3, 'Space', 'OK', true)
;

CREATE TABLE `employees` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(15),
	`last_name` VARCHAR(15),
    `title` VARCHAR(30),
    `notes` VARCHAR(100)
);

INSERT INTO `employees` (`first_name`, `last_name`, `title`, `notes`)
VALUES
('Angel', 'Velkov', 'programmer', NULL),
('Hristo', 'Hristov', 'artist', NULL),
('Georgi', 'Georgiev', 'dirver', NULL),
('Peter', 'Petrov', 'astrounaut', NULL),
('Ivan', 'Ivanov', 'policeman', NULL)
;

CREATE TABLE `customers` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `driver_licence_number` INT NOT NULL,
    `full_name` VARCHAR(30) NOT NULL,
    `address` VARCHAR(100),
    `city` VARCHAR(60),
    `zip_code` INT,
    `notes` VARCHAR(100)
);

INSERT INTO `customers` (`driver_licence_number`, `full_name`, `address`, `city`, `zip_code`, `notes`)
VALUES
(1, 'Primer Primerov', NULL, 'Sofia', 1000, NULL),
(13, 'Vtori Primerov', NULL, 'Plovdiv', 2000, NULL),
(333, 'Treti Primerov', NULL, 'Varna', 3000, NULL),
(420, 'Chetvurti Primerov', NULL, NULL, NULL, NULL),
(69, 'Peti Primerov', NULL, 'Sofia', 5000, NULL)
;

CREATE TABLE `rental_orders` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `employee_id` INT NOT NULL,
    `cutomer_id` INT NOT NULL,
    `car_id` INT NOT NULL,
    `car_condition` VARCHAR(100) NOT NULL,
	`tank_level` INT NOT NULL,
    `killometrage_start` INT NOT NULL,
    `killometrage_end` INT NOT NULL,
    `total_killometrage` INT NOT NULL,
	`start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
    `total_days` INT NOT NULL,
	`rate_applied` DOUBLE NOT NULL,
    `tax_rate` DOUBLE NOT NULL,
	`order_status` VARCHAR(30) NOT NULL,
    `notes` VARCHAR(100),
    
    CONSTRAINT `fk_rental_orders-employees`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employees` (`id`),
    
    CONSTRAINT `fk_rental_orders-customers`
    FOREIGN KEY (`cutomer_id`)
    REFERENCES `customers` (`id`),
    
    CONSTRAINT `fk_rental_orders-cars`
    FOREIGN KEY (`car_id`)
    REFERENCES `cars` (`id`)
);

INSERT INTO `rental_orders` (`employee_id`, `cutomer_id`, `car_id`, `car_condition`,`tank_level`,
	`killometrage_start`, `killometrage_end`, `total_killometrage`, `start_date`,
	`end_date`, `total_days` , `rate_applied`,`tax_rate`, `order_status`, `notes`)
VALUES
(1, 1, 1, 'OK', 100, 12, 15, 3, '2000-01-01', '2000-01-02', 2, 0.22, 18.9, 'OK', NULL),
(2, 2, 2, 'OK', 200, 12, 15, 3, '2001-01-01', '2001-01-02', 2, 0.32, 28.9, 'REFUSED', NULL),
(3, 3, 3, 'OK', 300, 12, 15, 3, '2002-01-01', '2002-01-02', 2, 0.42, 38.9, 'OK', NULL),
(4, 4, 4, 'BAD', 400, 12, 15, 3, '2003-01-01', '2003-01-02', 2, 0.52, 48.9, 'OK', NULL),
(5, 5, 5, 'OK', 500, 12, 15, 3, '2004-01-01', '2004-01-02', 2, 0.62, 58.9, 'OK', NULL)
;

SELECT * FROM `rental_orders`;
