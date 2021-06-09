USE `soft_uni`;

SELECT `first_name`, `last_name`, `hire_date`,
	`d`.`name` AS `dept_name`
FROM `employees`
JOIN `departments` AS `d` USING(`department_id`)
WHERE DATE(`hire_date`) > '1999-01-01' AND `d`.`name` IN ('Sales', 'Finance')
ORDER BY `hire_date`;