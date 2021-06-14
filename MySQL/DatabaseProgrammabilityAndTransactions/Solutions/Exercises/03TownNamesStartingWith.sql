USE `soft_uni`;

DROP PROCEDURE IF EXISTS `usp_get_towns_starting_with`;

DELIMITER $$
CREATE PROCEDURE `usp_get_towns_starting_with`(start_str VARCHAR(50))
BEGIN
	SELECT `name`
    FROM `towns`
    WHERE `name` LIKE CONCAT(start_str, '%')
    ORDER BY `name`;
END$$
DELIMITER ;

CALL `usp_get_towns_starting_with`('b');