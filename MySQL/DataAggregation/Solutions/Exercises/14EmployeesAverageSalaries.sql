USE `soft_uni`;

SET SQL_SAFE_UPDATES = 0;

DROP TABLE IF EXISTS `employees_with_salary_more_than_3000`;

CREATE TABLE `employees_with_salary_more_than_3000`
AS SELECT * FROM `employees`
WHERE `salary` > 3000;

DELETE FROM `employees_with_salary_more_than_3000`
WHERE `manager_id` = 42;

UPDATE `employees_with_salary_more_than_3000`
SET `salary` = `salary` + 5000
WHERE `department_id` = 1;

SELECT `department_id`, AVG(`salary`) AS 'avg_salary'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;