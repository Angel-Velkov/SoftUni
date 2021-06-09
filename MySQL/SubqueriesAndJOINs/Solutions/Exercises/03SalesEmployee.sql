USE `soft_uni`;

SELECT `employee_id`, `first_name`, `last_name`,
	`d`.`name` AS 'department_name'
FROM `employees`
JOIN `departments` AS `d` USING(`department_id`)
WHERE `d`.`name` = 'Sales'
ORDER BY `employee_id` DESC;