USE `soft_uni`;

DROP PROCEDURE IF EXISTS `usp_deposit_money`;

DELIMITER $$
CREATE PROCEDURE `usp_deposit_money`(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	UPDATE `accounts`
    SET `balance` = `balance` + money_amount
    WHERE account_id = `id` AND money_amount > 0;
END$$
DELIMITER ;

SET @id = 1;

SELECT `id` AS 'account_id', `account_holder_id`, `balance`
FROM `accounts`
WHERE `id` = @id;

CALL usp_deposit_money(@id, 10);