USE `soft_uni`;

SELECT `employee_id`, `first_name`, `salary`,
	`d`.`name` AS 'department_name'
FROM `employees`
JOIN `departments` AS `d` USING(`department_id`)
WHERE `salary` > 15000
ORDER BY `d`.`department_id` DESC
LIMIT 5;