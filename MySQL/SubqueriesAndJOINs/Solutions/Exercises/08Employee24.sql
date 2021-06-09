USE `soft_uni`;

SELECT `employee_id`, `first_name`,
	IF(YEAR(`p`.`start_date`) >= 2005, NULL, `p`.`name`) AS 'project_name'
FROM `employees`
JOIN `employees_projects` USING(`employee_id`)
JOIN `projects` AS `p` USING(`project_id`)
WHERE `employee_id` = 24
ORDER BY `project_name`;