USE `soft_uni`;

DROP FUNCTION IF EXISTS `ufn_count_employees_by_town`;

DELIMITER //
CREATE FUNCTION `ufn_count_employees_by_town` (town_name VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE `emp_count` INT;
    SET `emp_count` := (SELECT COUNT(*)
		FROM `employees`
        JOIN `addresses` USING(`address_id`)
		JOIN `towns` AS `t` USING(`town_id`)
        WHERE `t`.`name` = `town_name`);
	RETURN `emp_count`;
END//
DELIMITER ;

SELECT `ufn_count_employees_by_town`('Sofia');
