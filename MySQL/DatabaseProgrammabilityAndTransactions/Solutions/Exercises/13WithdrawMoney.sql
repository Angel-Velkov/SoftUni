USE `soft_uni`;

DROP PROCEDURE IF EXISTS `usp_withdraw_money`;

DELIMITER $$
CREATE PROCEDURE `usp_withdraw_money`(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
    IF (money_amount > 0 AND
		(
		SELECT `balance` 
        FROM `accounts` 
        WHERE `id` = account_id
        ) >= money_amount
	) THEN UPDATE `accounts`
			SET `balance` = `balance` - money_amount
            WHERE `id` = account_id;
            COMMIT;
	ELSE ROLLBACK;
    END IF;
END$$
DELIMITER ;

SET @id = 1;

CALL usp_withdraw_money(@id, -95);

SELECT `id` AS 'account_id', `account_holder_id`, `balance`
FROM `accounts`
WHERE `id` = @id;