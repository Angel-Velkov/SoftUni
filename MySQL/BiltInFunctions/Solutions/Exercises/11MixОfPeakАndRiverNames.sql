USE `geography`;

SELECT `peak_name`, `river_name`, 
	concat(lower(`peak_name`), lower(`river_name`)) AS `mix`
FROM `peaks` AS `p`
	JOIN `rivers` AS `r`
    ON RIGHT(`p`.`peak_name`, 1) = LEFT(`r`.`river_name`, 1)
ORDER BY `mix`;