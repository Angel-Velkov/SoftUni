USE `soft_uni`;

DROP FUNCTION IF EXISTS `ufn_calculate_future_value`;

DELIMITER $$
CREATE FUNCTION `ufn_calculate_future_value`(sum DECIMAL(19, 4), yearly DECIMAL(6, 4), number_of_years INT)
RETURNS DECIMAL(19, 4)
DETERMINISTIC
BEGIN
	DECLARE result DECIMAL(19, 4);
    SET result = sum * POW((1 + yearly), number_of_years);
    RETURN result;
END$$
DELIMITER ;

SELECT `ufn_calculate_future_value`(1000, 0.5, 5);