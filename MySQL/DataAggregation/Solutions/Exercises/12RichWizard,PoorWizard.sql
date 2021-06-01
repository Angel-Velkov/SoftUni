USE `gringotts`;

SELECT SUM(`result`.`difference`) AS 'sum_differdence'
FROM (
	SELECT `deposit_amount` - (
		SELECT `deposit_amount` 
		FROM `wizzard_deposits` 
		WHERE `id` = `wizzard_deposits_1`.`id` + 1
	) AS 'difference'
    FROM `wizzard_deposits` AS `wizzard_deposits_1`
) AS `result`;