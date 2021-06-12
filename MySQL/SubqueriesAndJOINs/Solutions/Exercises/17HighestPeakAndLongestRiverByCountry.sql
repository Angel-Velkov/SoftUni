USE `geography`;

SELECT `country_name`, MAX(`elevation`) AS 'highest_peak_elevation'
	, MAX(`length`) AS 'longest_river_length'
FROM `countries`
JOIN `countries_rivers` AS cr USING(`country_code`)
JOIN `rivers` AS r ON r.`id` = cr.`river_id`
JOIN `mountains_countries` AS mc USING(`country_code`)
JOIN `mountains` AS m ON m.`id` = mc.`mountain_id`
JOIN `peaks` AS p ON m.`id` = p.`mountain_id`
GROUP BY `country_name`
ORDER BY `highest_peak_elevation` DESC,
	`longest_river_length` DESC,
	`country_name` ASC
LIMIT 5;