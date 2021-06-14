DROP DATABASE IF EXISTS `soft_uni`;
CREATE DATABASE `soft_uni`;
USE `soft_uni`;

CREATE TABLE `employees`(
	`employee_id` INT PRIMARY KEY AUTO_INCREMENT,
	`first_name` VARCHAR(50),
	`last_name` VARCHAR(50),
	`middle_name` VARCHAR(50),
	`job_title` VARCHAR(50),
	`department_id` INT,
	`salary` DECIMAL(19,4)
);

INSERT INTO `employees`
VALUES
	(1, 'Guy', 'Gilbert', 'R', 'Production Technician', 7, 12500.0000),
	(2, 'Kevin', 'Brown', 'F', 'Marketing Assistant', 4, 13500.0000),
	(3, 'Roberto', 'Tamburello', NULL, 'Engineering Manager', 1, 43300.0000),
	(4, 'Rob', 'Walters', NULL, 'Senior Tool Designer', 2, 29800.0000),
	(5, 'Thierry', 'D\'Hers', 'B', 'Tool Designer', 2, 25000.0000),
	(6, 'David', 'Bradley', 'M', 'Marketing Manager', 5, 37500.0000),
	(7, 'JoLynn', 'Dobney', 'M', 'Production Supervisor', 7, 25000.0000),
	(8, 'Ruth', 'Ellerbrock', 'Ann', 'Production Technician', 7, 13500.0000),
	(9, 'Gail', 'Erickson', 'A', 'Design Engineer', 1, 32700.0000),
	(10, 'Barry', 'Johnson', 'K', 'Production Technician', 7 , 13500.0000)
;


CREATE TABLE `deleted_employees` (
	`employee_id` INT PRIMARY KEY AUTO_INCREMENT,
	`first_name` VARCHAR(50),
	`last_name` VARCHAR(50),
	`middle_name` VARCHAR(50),
	`job_title` VARCHAR(50),
	`department_id` INT,
	`salary` DECIMAL(19,4)
);

DELIMITER $$
CREATE TRIGGER `tr_deleted_employees`
AFTER DELETE
ON `employees`
FOR EACH ROW
BEGIN
INSERT INTO `deleted_employees`
	(`first_name`, `last_name`, `middle_name`, `job_title`,
		`department_id`, `salary`)
VALUES
(OLD.`first_name`, OLD.`last_name`, OLD.`middle_name`, OLD.`job_title`,
	OLD.`department_id`, OLD.`salary`);
END$$
DELIMITER ;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM `employees`
WHERE `employee_id` = 1;