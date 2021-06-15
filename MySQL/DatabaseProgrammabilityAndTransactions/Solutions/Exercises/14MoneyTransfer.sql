USE `soft_uni`;

# From 12. Deposit Money
DROP PROCEDURE IF EXISTS `usp_deposit_money`;

DELIMITER $$
CREATE PROCEDURE `usp_deposit_money`(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	UPDATE `accounts`
    SET `balance` = `balance` + money_amount
    WHERE account_id = `id` AND money_amount > 0;
END$$
DELIMITER ;

#---------------------------------------------

#From 13. Withdraw Money

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

#---------------------------------------------

DROP PROCEDURE IF EXISTS `usp_transfer_money`;

DELIMITER $$
CREATE PROCEDURE `usp_transfer_money`(from_account_id INT, to_account_id INT, amount DECIMAL(19, 4))
BEGIN
	IF (from_account_id != to_account_id
		AND amount > 0
        AND (SELECT COUNT(*) FROM `accounts` WHERE `id` IN (from_account_id, to_account_id)) = 2
        AND (SELECT `balance` FROM `accounts` WHERE `id` = from_account_id) - amount >= 0
	) THEN START TRANSACTION;
		CALL usp_withdraw_money(from_account_id, amount);
		CALL usp_deposit_money(to_account_id, amount);
    END IF;
END$$
DELIMITER ;

SET @first_id = 3;
SET @second_id = 4;

CALL usp_transfer_money(@first_id, @second_id, 90);

SELECT `id` AS 'account_id', `account_holder_id`, `balance`
FROM `accounts`
WHERE `id` IN (@first_id, @second_id);