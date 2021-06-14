USE `soft_uni`;

DROP PROCEDURE IF EXISTS `usp_increment`;

DELIMITER $$
CREATE PROCEDURE `usp_increment` (INOUT `num` INT)
BEGIN
	SET `num` := `num` + 1;
END$$
DELIMITER ;

DROP FUNCTION IF EXISTS `ufn_is_word_comprised`;

DELIMITER $$
CREATE FUNCTION `ufn_is_word_comprised`(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
	DECLARE result BOOLEAN;
    DECLARE pointer INT;
    DECLARE `length` INT;
    SET result = TRUE;
    SET pointer = 0;
	SET `length` = CHAR_LENGTH(word);
	my_loop: WHILE(pointer <= `length`)
    DO
		IF LOCATE(SUBSTR(word, pointer, 1), set_of_letters) = 0
			THEN SET result := FALSE;
            LEAVE my_loop;
		END IF;
        SET pointer = pointer + 1;
    END WHILE;
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
