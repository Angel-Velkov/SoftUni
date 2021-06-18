DROP DATABASE IF EXISTS `model`;

CREATE DATABASE `model`;
USE `model`;

CREATE TABLE `people` (
	`person_id` INT NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `salary` DECIMAL(17, 2),
    `passport_id` INT
);

ALTER TABLE `people`
ADD CONSTRAINT `pk_id`
PRIMARY KEY (`person_id`);

ALTER TABLE `people`
ADD CONSTRAINT `uq_passport`
UNIQUE (`passport_id`);

CREATE TABLE `passports` (
	`passport_id` INT PRIMARY KEY NOT NULL,
    `passport_number` VARCHAR(8) NOT NULL
);

ALTER TABLE `passports`
ADD CONSTRAINT `uq_passport_number`
UNIQUE (`passport_number`);

ALTER TABLE `people`
ADD CONSTRAINT `fk_people_passports` FOREIGN KEY (`passport_id`)
	REFERENCES `passports` (`passport_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
;

INSERT INTO `passports`
VALUES
	(101, 'N34FG21B'),
    (102, 'K65LO4R7'),
    (103, 'ZE657QP2')
;

INSERT INTO `people`
VALUES
	(1, 'Roberto', 43300.00, 102),
    (2, 'Tom', 56100.00, 103),
    (3, 'Yana', 60200.00, 101)
;

SELECT `person_id`, `first_name`, `salary`,
	pass.`passport_id`, `passport_number`
FROM `people` AS ppl
JOIN `passports` AS pass
ON ppl.`passport_id` = pass.`passport_id`;