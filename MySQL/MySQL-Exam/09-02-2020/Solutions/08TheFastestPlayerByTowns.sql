USE `fsd`;

SELECT MAX(`speed`) AS 'max_speed', twn.`name` AS 'town_name'
FROM `towns` AS twn
LEFT JOIN `stadiums` AS s ON twn.`id` = s.`town_id`
LEFT JOIN `teams` AS t ON s.`id` = t.`stadium_id`
LEFT JOIN `players` AS p ON p.`team_id` = t.`id`
LEFT JOIN `skills_data` AS sd ON sd.`id` = p.`skills_data_id`
WHERE t.`name` != 'Devify'
GROUP BY twn.`id`
ORDER BY `max_speed` DESC, `town_name`;