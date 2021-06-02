USE `soft_uni`;

SELECT `department_id`, SUM(`salary`) AS 'total_salary'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;
