USE `gringotts`;

SELECT SUBSTR(`first_name` FROM 1 FOR 1) AS 'first_letter'
FROM `wizzard_deposits`
WHERE `deposit_group` = 'Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter`;