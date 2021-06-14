USE `soft_uni`;

DROP FUNCTION IF EXISTS `ufn_is_word_comprised`;

DELIMITER $$
CREATE FUNCTION `ufn_is_word_comprised`(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
	DECLARE result INT;
	DECLARE counter INT;
	DECLARE current_char VARCHAR(30);
	SET result = 1;
	SET counter = 0;
	REPEAT
		SET current_char = SUBSTRING(word, counter, 1);
		SET result = IF(set_of_letters NOT LIKE CONCAT('%', current_char, '%'), 0, 1);
		SET counter = counter + 1;
	UNTIL result = 0 OR counter = CHAR_LENGTH(word) + 1
    END REPEAT;
    RETURN result;
END$$
DELIMITER ;

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
	`set_of_letters` VARCHAR(50),
    `word` VARCHAR(50)
);

INSERT INTO `test`
VALUES
('oistmiahf', 'Sofia'),
('oistmiahf', 'halves'),
('bobr', 'Rob'),
('pppp', 'Guy');

SELECT `set_of_letters`, `word`, ufn_is_word_comprised(`set_of_letters`, `word`) AS 'result'
FROM `test`;