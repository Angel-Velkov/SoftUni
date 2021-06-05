DROP SCHEMA IF EXISTS `car_rental`;

CREATE DATABASE `car_rental`;
USE `car_rental`;

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY NOT NULL,
    `category` VARCHAR(30) NOT NULL,
    `daily_rate` DECIMAL(3, 1) NOT NULL,
    `weekly_rate` DECIMAL(3, 1) NOT NULL,
    `monthly_rate` DECIMAL(3, 1) NOT NULL,
    `weekend_rate` DECIMAL(3, 1) NOT NULL
);

CREATE TABLE `cars` (
	`id` INT PRIMARY KEY NOT NULL,
    `plate_number` CHAR(6) UNIQUE NOT NULL,
    `make` VARCHAR(30) NOT NULL,
    `model` VARCHAR(30),
    `car_year` YEAR NOT NULL,
    `category_id` INT NOT NULL,
    `doors` INT NOT NULL,
    `picture` BLOB,
    `car_condition` VARCHAR(100),
	`available` TINYINT NOT NULL
);

CREATE TABLE `employees` (
	`id` INT PRIMARY KEY NOT NULL,
    `first_name` VARCHAR(15) NOT NULL,
	`last_name` VARCHAR(15) NOT NULL,
    `title` VARCHAR(30) NOT NULL,
    `notes` VARCHAR(100)
);

CREATE TABLE `customers` (
	`id` INT PRIMARY KEY NOT NULL,
    `driver_license_number` INT NOT NULL,
    `full_name` VARCHAR(30) NOT NULL,
    `address` VARCHAR(100),
    `city` VARCHAR(50),
    `zip_code` INT,
    `notes` VARCHAR(100)
);

CREATE TABLE `rental_orders` (
	`id` INT PRIMARY KEY NOT NULL,
    `employee_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    `car_id` INT NOT NULL,
    `car_condition` VARCHAR(100) NOT NULL,
	`tank_level` INT NOT NULL,
    `kilometrage_start` INT NOT NULL,
    `kilometrage_end` INT NOT NULL,
    `total_kilometrage` INT NOT NULL,
	`start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
    `total_days` INT NOT NULL,
	`rate_applied` DECIMAL(2, 1),
    `tax_rate` DECIMAL(2, 1),
	`order_status` VARCHAR(30),
    `notes` VARCHAR(100)
);

INSERT INTO `categories`
VALUES
(1, 'van', 11.9, 12.9, 13.9, 14.9),
(2, 'sedan', 21.9, 22.9, 23.9, 24.9),
(3, 'bus', 32.9, 32.9, 33.9, 34.9)
;

INSERT INTO `cars`
VALUES
(1, 'AZ0694', '2000-12-01', 'Ferrari', 1997, 1, 2, 'Horse', 'OK', TRUE),
(2, 'RS0324', '2001-07-05', 'Opel', 1995, 2, 4, 'Landfill', 'RUSTY', TRUE),
(3, 'SS6445', '1997-11-13', 'Ford', 1997, 3, 4, 'Crashed into a tree', 'BAD', FALSE)
;

INSERT INTO `employees`
VALUES
(1, 'Angel', 'Velkov', 'programmer', NULL),
(2, 'Hristo', 'Hristov', 'artist', NULL),
(3, 'Georgi', 'Georgiev', 'dirver', NULL)
;

INSERT INTO `customers`
VALUES
(1, 1, 'Primer Primerov', NULL, 'Sofia', 1000, NULL),
(2, 13, 'Vtori Primerov', NULL, 'Plovdiv', 2000, NULL),
(3, 333, 'Treti Primerov', NULL, 'Varna', 3000, NULL)
;

INSERT INTO `rental_orders`
VALUES
(1, 1, 1, 1, 'OK', 100, 12, 15, 3, '2000-01-01', '2000-01-02', 2, 1.9, 8.9, 'OK', NULL),
(2, 2, 2, 2, 'OK', 200, 12, 15, 3, '2001-01-01', '2001-01-02', 2, 2.2, 1.1, 'REFUSED', NULL),
(3, 3, 3, 3, 'OK', 300, 12, 15, 3, '2002-01-01', '2002-01-02', 2, 8.4, 3.9, 'OK', NULL)
;

SELECT * FROM `rental_orders`;