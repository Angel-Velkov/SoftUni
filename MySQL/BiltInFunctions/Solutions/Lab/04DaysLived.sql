SELECT CONCAT(`first_name`, ' ', `last_name`) AS 'FULL NAME',
TIMESTAMPDIFF(DAY, `born`, `died`) AS 'Days Lived'
FROM `authors`;