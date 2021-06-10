USE `geography`;

SELECT `country_code`, COUNT(`m`.`id`) AS `m_count`
FROM `mountains_countries` AS `mc`
JOIN `mountains` AS `m` ON `m`.`id` = `mc`.`mountain_id`
WHERE `country_code` IN ('US', 'RU', 'BG')
GROUP BY `country_code`
ORDER BY `m_count` DESC;