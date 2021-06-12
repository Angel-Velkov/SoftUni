USE `geography`;

SELECT `country_name`, `river_name`
FROM `countries` 
LEFT JOIN `countries_rivers` AS `cr` USING(`country_code`)
LEFT JOIN `rivers` AS `r` ON `r`.`id` = `cr`.`river_id`
WHERE `continent_code` = 'AF'
ORDER BY `country_name`
LIMIT 5;