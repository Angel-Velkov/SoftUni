USE `fsd`;

DROP PROCEDURE IF EXISTS `udp_find_playmaker`;

DELIMITER $$
CREATE PROCEDURE `udp_find_playmaker`(min_dribble_points INT, team_name VARCHAR(45))
BEGIN
	SELECT CONCAT(`first_name`, ' ', `last_name`) AS 'full_name',
		`age`, `salary`, `dribbling`, `speed`, `team_name`
	FROM `players` AS p
    LEFT JOIN `skills_data` AS s ON p.`skills_data_id` = s.`id`
    LEFT JOIN `teams` AS t ON p.`team_id` = t.`id`
    WHERE s.dribbling > min_dribble_points
		AND t.`name` = team_name
        AND s.`speed` > (
			SELECT AVG(`speed`)
            FROM `skills_data` AS s
			JOIN `players` AS p ON p.`skills_data_id` = s.`id`
            JOIN `teams` AS t ON p.`team_id` = t.`id`
            WHERE t.`name` = team_name
		)
	ORDER BY `speed` DESC, `dribbling` DESC
    LIMIT 1;
END$$
DELIMITER ;

CALL udp_find_playmaker(20, 'Skyble');