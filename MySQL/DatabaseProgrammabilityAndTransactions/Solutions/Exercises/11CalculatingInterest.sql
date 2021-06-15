USE `soft_uni`;

#From previous problem!!!

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

#------------------------------------------------------------------

DROP PROCEDURE IF EXISTS `usp_calculate_future_value_for_account`;

DELIMITER $$
CREATE PROCEDURE `usp_calculate_future_value_for_account`(account_id INT, interest_rate DECIMAL(6,4))
BEGIN
	SELECT a.`id` AS 'account_id', `first_name`, `last_name`, `balance` AS 'current_balance',
		ufn_calculate_future_value(`balance`, interest_rate, 5) AS 'balance_in_5_years'
	FROM `accounts` AS a
    JOIN `account_holders` AS ah ON ah.`id` = a.`account_holder_id`
    WHERE a.`id` = account_id;
END$$
DELIMITER ;

CALL usp_calculate_future_value_for_account(1, 0.1);