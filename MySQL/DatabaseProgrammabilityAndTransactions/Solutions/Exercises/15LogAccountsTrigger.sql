USE `soft_uni`;

CREATE TABLE `logs`(
	`log_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	`account_id` INT NOT NULL,
    `old_sum` DECIMAL(19, 4) NOT NULL,
    `new_sum` DECIMAL(19, 4)
);

DELIMITER $$
CREATE TRIGGER `tr_logs`
AFTER UPDATE
ON `accounts`
FOR EACH ROW
BEGIN
INSERT INTO `logs`
	(`account_id`, `old_sum`, `new_sum`)
VALUES
	(NEW.`id`, OLD.`balance`, NEW.`balance`);
END$$
DELIMITER ;

SELECT * FROM `logs`;