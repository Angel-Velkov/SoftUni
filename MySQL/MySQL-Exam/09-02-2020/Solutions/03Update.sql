USE `fsd`;

SET SQL_SAFE_UPDATES = 0;

UPDATE `coaches` AS c
SET `coach_level` = `coach_level` + 1
WHERE LEFT(`first_name`, 1) = 'A'
	AND (
		SELECT COUNT(*)
		FROM `players_coaches` AS pc
        WHERE c.`id` = pc.`coach_id`
			AND pc.`player_id` IS NOT NULL
		) > 0
;

SELECT `coach_level`
FROM `coaches`
WHERE LEFT(`first_name`, 1) = 'A';