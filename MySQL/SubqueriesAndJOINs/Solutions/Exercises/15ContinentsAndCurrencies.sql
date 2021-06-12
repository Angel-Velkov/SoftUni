USE `geography`;

SELECT d1.`continent_code`, d1.`currency_code`, d1.`currency_usage` 
FROM 
	(SELECT c.`continent_code`, c.`currency_code`, COUNT(c.`currency_code`) AS 'currency_usage'
    FROM `countries` AS c
	GROUP BY c.`currency_code`, c.`continent_code`
    HAVING `currency_usage` > 1) AS d1
LEFT JOIN 
	(SELECT c.`continent_code`, c.`currency_code`, COUNT(c.`currency_code`) AS 'currency_usage'
    FROM `countries` AS c
	GROUP BY c.`currency_code`, c.`continent_code`
    HAVING `currency_usage` > 1) AS d2
ON d1.`continent_code` = d2.`continent_code` AND d2.`currency_usage` > d1.`currency_usage`
WHERE d2.`currency_usage` IS NULL
ORDER BY d1.`continent_code`, d1.`currency_code`;

#--------------------------------------------------

CREATE TABLE IF NOT EXISTS `continents_currencies` 
AS SELECT c.`continent_code`, c.`currency_code`, COUNT(c.`currency_code`) AS 'currency_usage' 
FROM `countries` AS c
GROUP BY c.`continent_code`,c.`currency_code`
HAVING `currency_usage` > 1
ORDER BY c.`continent_code` , c.`currency_code`;
 
SELECT cc.*
FROM `continents_currencies` AS cc
LEFT JOIN `continents_currencies` AS cc2
ON cc.`continent_code` = cc2.`continent_code`
        AND cc.`currency_usage` < cc2.`currency_usage`
WHERE cc2.`currency_usage` IS NULL;
    
DROP TABLE `continents_currencies`;

