USE `soft_uni`;

SELECT `first_name`, `last_name`, round(`salary`, 2) AS 'salary'
FROM `employees`
ORDER BY `employee_id`;