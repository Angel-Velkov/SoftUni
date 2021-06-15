USE `soft_uni`;

CREATE TABLE `notification_emails` (
	`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `recipient` INT NOT NULL,
    `subject` VARCHAR(30) NOT NULL,
    `body` VARCHAR(100) NOT NULL
);

DELIMITER $$
CREATE TRIGGER `tr_balance_changes`
AFTER UPDATE
ON `accounts`
FOR EACH ROW
BEGIN
INSERT INTO `notification_emails`
	(`recipient`, `subject`, `body`)
VALUES
	(NEW.`id`,
    CONCAT('Balance change for account: ', NEW.`id`),
    CONCAT('On ', NOW(),' your balance was changed from ', OLD.`balance`, ' to ', NEW.`balance`, ' .'));
END$$
DELIMITER ;

SELECT * FROM `notofication_emails`;