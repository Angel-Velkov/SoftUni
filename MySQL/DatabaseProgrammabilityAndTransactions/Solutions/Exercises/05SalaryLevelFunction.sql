USE `soft_uni`;

DROP FUNCTION IF EXISTS `ufn_get_salary_level`;

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

SET @salary = 50000;

SELECT @salary AS 'salary', `ufn_get_salary_level`(@salary) AS 'salary_level';