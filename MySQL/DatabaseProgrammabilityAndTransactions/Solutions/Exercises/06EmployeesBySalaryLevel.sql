USE `soft_uni`;

## From previous problem!!!

DROP TABLE IF EXISTS `ufn_get_salary_level`;

DELIMITER $$
CREATE FUNCTION `ufn_get_salary_level`(amount DECIMAL)
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
	RETURN(CASE
				WHEN amount < 30000 THEN 'Low'
                WHEN amount <= 50000 THEN 'Average'
                ELSE 'High'
			END);
END$$
DELIMITER ;

#----------------------------------------------------------------

DROP PROCEDURE IF EXISTS `usp_get_employees_by_salary_level`;

DELIMITER $$
CREATE PROCEDURE `usp_get_employees_by_salary_level`(salary_level VARCHAR(10))
BEGIN
	SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE ufn_get_salary_level(`salary`) = salary_level
    ORDER BY `first_name` DESC, `last_name` DESC;
END$$
DELIMITER ;

CALL `usp_get_employees_by_salary_level`('high');