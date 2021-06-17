USE `fsd`;

SELECT `name` AS 'team_name', `established`, `fan_base`, COUNT(p.`id`) AS 'players_count'
FROM `teams` AS t
LEFT JOIN `players` AS p ON p.`team_id` = t.`id`
GROUP BY t.`id`
ORDER BY `players_count` DESC, `fan_base` DESC;