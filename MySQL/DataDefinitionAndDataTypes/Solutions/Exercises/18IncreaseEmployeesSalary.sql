USE `soft_uni`;

SET SQL_SAFE_UPDATES = 0;

UPDATE `employees`
SET `salary` = `salary` * 1.1;

SELECT `salary` FROM `employees`;