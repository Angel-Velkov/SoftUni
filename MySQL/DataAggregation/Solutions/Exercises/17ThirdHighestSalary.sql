USE `soft_uni`;

SELECT `e`.`department_id`,
	(SELECT DISTINCT `salary`
		FROM `employees` AS `e2`
		WHERE `e2`.`department_id` = `e`.`department_id`
		ORDER BY `e2`.`salary` DESC
		LIMIT 1 OFFSET 2) AS 'third_highest_salary'
FROM `employees` AS `e`
GROUP BY `department_id`
HAVING `third_highest_salary` IS NOT NULL
ORDER BY `department_id`;