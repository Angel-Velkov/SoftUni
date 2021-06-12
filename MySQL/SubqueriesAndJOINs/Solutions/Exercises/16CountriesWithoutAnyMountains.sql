USE `geography`;

SELECT COUNT(`country_code`)
FROM `countries` AS c1
WHERE c1.`country_code` NOT IN 
	(SELECT `country_code`
    FROM `mountains_countries`);