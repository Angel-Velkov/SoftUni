USE `fsd`;

SELECT c.`name`, COUNT(p.`id`) AS 'total_count_of_players', SUM(p.`salary`) AS 'total_sum_of_salaries'
FROM `countries` AS c
LEFT JOIN `towns` AS twn ON twn.`country_id` = c.`id`
LEFT JOIN `stadiums` AS s ON s.`town_id` = twn.`id`
LEFT JOIN `teams` AS t ON t.`stadium_id` = s.`id`
LEFT JOIN `players` AS p ON p.`team_id` = t.`id`
GROUP BY c.`name`
ORDER BY `total_count_of_players` DESC, c.`name`;