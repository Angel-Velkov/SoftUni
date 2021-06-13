USE `soft_uni`;

DELIMITER $$
CREATE PROCEDURE `usp_raise_salaries`(`department_name` VARCHAR(50))
BEGIN
	UPDATE `employees` AS e
    JOIN `departments` AS d USING(`department_id`)
    SET `salary` = `salary` * 1.05
    WHERE d.`name` = `department_name`;
END$$
DELIMITER ;

CALL `usp_raise_salaries`('Sales');

SELECT `first_name`, `salary`
FROM `employees` AS e
JOIN `departments` AS d USING(`department_id`)
WHERE d.`name` = 'Sales';