USE `soft_uni`;

DROP PROCEDURE IF EXISTS `usp_get_holders_with_balance_higher_than`;

DELIMITER $$
CREATE PROCEDURE `usp_get_holders_with_balance_higher_than`(amount DECIMAL)
BEGIN
	SELECT `first_name`, `last_name`
    FROM `account_holders` AS ah
    RIGHT JOIN `accounts` AS a ON ah.`id` = a.`account_holder_id`
    GROUP BY ah.`id`
    HAVING SUM(`balance`) > amount
    ORDER BY ah.`id`;    
END$$
DELIMITER ;

CALL `usp_get_holders_with_balance_higher_than`(7000);