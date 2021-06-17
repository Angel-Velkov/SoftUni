USE `fsd`;

DROP FUNCTION IF EXISTS `udf_stadium_players_count`;

DELIMITER $$
CREATE FUNCTION `udf_stadium_players_count`(stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE players_count INT;
    SET players_count := (
		SELECT COUNT(*)
        FROM `stadiums` AS s
        JOIN `teams` AS t ON t.`stadium_id` = s.`id`
        JOIN `players` AS p ON p.`team_id` = t.`id`
        WHERE s.`name` = stadium_name
	);
    RETURN players_count;
END$$
DELIMITER ;

SELECT udf_stadium_players_count('Jaxworks') AS 'count';