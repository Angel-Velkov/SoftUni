USE `geography`;

SELECT `peak_name`, `river_name`, 
	CONCAT(LOWER(`peak_name`), SUBSTR(LOWER(`river_name`), 2)) AS `mix`
FROM `peaks` AS `p`
	JOIN `rivers` AS `r`
    ON RIGHT(`p`.`peak_name`, 1) = LEFT(`r`.`river_name`, 1)
ORDER BY `mix`;