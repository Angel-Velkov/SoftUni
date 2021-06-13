USE `soft_uni`;

CREATE TABLE `deleted_employeest` (
  `employee_id` int NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `job_title` varchar(50) NOT NULL,
  `department_id` int NOT NULL,
  `manager_id` int DEFAULT NULL,
  `hire_date` timestamp(6) NOT NULL,
  `salary` decimal(19,4) NOT NULL,
  `address_id` int DEFAULT NULL
);

DELIMITER $$
CREATE TRIGGER `tr_deleted_employees`
AFTER DELETE
ON `employees`
FOR EACH ROW
BEGIN
INSERT INTO `soft_uni`.`deleted_employeest`
(`employee_id`,
`first_name`,
`last_name`,
`middle_name`,
`job_title`,
`department_id`,
`manager_id`,
`hire_date`,
`salary`,
`address_id`)
VALUES
(OLD.`employee_id`, OLD.`first_name`, OLD.`last_name`, OLD.`middle_name`, OLD.`job_title`,
	OLD.`department_id`, OLD.`manager_id`, OLD.`hire_date`, OLD.`salary`, OLD.`address_id`);
END$$
DELIMITER ;