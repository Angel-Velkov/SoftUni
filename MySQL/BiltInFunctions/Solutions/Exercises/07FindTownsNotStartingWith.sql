USE `soft_uni`;

SELECT `town_id`, `name`
FROM `towns`
WHERE left(`name`, 1) NOT IN ('R', 'B', 'D')
ORDER BY `name`;