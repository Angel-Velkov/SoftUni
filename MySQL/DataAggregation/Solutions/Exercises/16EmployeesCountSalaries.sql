USE `soft_uni`;

SELECT COUNT(`salary`) AS 'count'
FROM `employees`
WHERE `manager_id` IS NULL;