USE `soft_uni`;

SELECT `employee_id`, `first_name`, `p`.`name` AS 'project_name'
FROM `employees`
JOIN `employees_projects` USING(`employee_id`)
JOIN `projects` AS `p` USING(`project_id`)
WHERE DATE(`p`.`start_date`) > '2002-08-13' AND `p`.`end_date` IS NULL
ORDER BY `first_name`, `p`.`name`
LIMIT 5;