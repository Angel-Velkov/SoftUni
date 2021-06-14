USE `soft_uni`;

DROP PROCEDURE IF EXISTS `usp_get_employees_from_town`;

DELIMITER $$
CREATE PROCEDURE `usp_get_employees_from_town`(town_name VARCHAR(50))
BEGIN
	SELECT `first_name`, `last_name`
    FROM `employees` AS e
    JOIN `addresses` AS a USING(`address_id`)
    JOIN `towns` AS t USING(`town_id`)
    WHERE t.`name` LIKE town_name
    ORDER BY `first_name`, `last_name`, `employee_id`;
END$$
DELIMITER ;

CALL `usp_get_employees_from_town`('Sofia');