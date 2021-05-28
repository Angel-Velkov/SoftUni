USE `soft_uni`;

SELECT `name`
FROM `towns`
WHERE length(`name`) IN (5, 6)
ORDER BY `name`;