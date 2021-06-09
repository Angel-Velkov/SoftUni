USE `soft_uni`;

SELECT `employee_id`, `first_name`
FROM `employees`
WHERE `employee_id` NOT IN 
	(
    SELECT `ep`.`employee_id`
	FROM `employees_projects` AS `ep`
    )
ORDER BY `employee_id` DESC
LIMIT 3;
