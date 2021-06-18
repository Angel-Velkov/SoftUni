DROP DATABASE IF EXISTS `model`;

CREATE DATABASE `model`;
USE `model`;

CREATE TABLE `manufacturers` (
	`manufacturer_id` INT NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `established_on` DATE NOT NULL
);

ALTER TABLE `manufacturers`
ADD CONSTRAINT `pk_id`
PRIMARY KEY (`manufacturer_id`);

CREATE TABLE `models` (
	`model_id` INT NOT NULL,
    `name` VARCHAR(30) NOT NULL,
    `manufacturer_id` INT NOT NULL
);

ALTER TABLE `models`
ADD CONSTRAINT `pk_id`
PRIMARY KEY (`model_id`);

ALTER TABLE `models`
ADD CONSTRAINT `fk_models_manufacturers`
FOREIGN KEY (`manufacturer_id`)
REFERENCES `manufacturers`(`manufacturer_id`);

INSERT INTO `manufacturers`
VALUES
	(1, 'BMW', '1916-03-01'),
    (2, 'Tesla', '2003-01-01'),
    (3, 'Lada', '1966-05-01')
;

INSERT INTO `models`
VALUES
	(101, 'X1', 1),
    (102, 'i6', 1),
    (103, 'Model S', 2),
    (104, 'Model X', 2),
    (105, 'Model 3', 2),
    (106, 'Nova', 3)
;

SELECT `mod`.`model_id`, `mod`.`name` AS 'model',
	`man`.`manufacturer_id`, `man`.`name` AS 'brand', `man`.`established_on`
FROM `models` AS `mod`
JOIN `manufacturers` AS `man`
ON `man`.`manufacturer_id` = `mod`.`manufacturer_id`;