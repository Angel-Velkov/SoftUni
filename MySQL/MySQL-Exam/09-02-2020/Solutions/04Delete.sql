USE `fsd`;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM `players` AS p
WHERE p.`age` >= 45;