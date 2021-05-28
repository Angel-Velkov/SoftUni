USE `soft_uni`;

SELECT `town_id`, `name`
FROM `towns`
WHERE left(`name`, 1) IN ('M', 'K', 'B')
ORDER BY `name`;