USE `soft_uni`;

DROP PROCEDURE IF EXISTS `usp_raise_salary_by_id`;

DELIMITER $$
CREATE PROCEDURE `usp_raise_salary_by_id`(`id` INT)
BEGIN
	START TRANSACTION;
	IF(
		(SELECT `employee_id`
        FROM `employees` AS e
        WHERE e.`employee_id` = `id`) = 1
	)THEN ROLLBACK;
    ELSE
		UPDATE `employees` AS e
        SET `salary` = `salary` * 1.05
        WHERE e.`employee_id` LIKE `id`;
        COMMIT;
	END IF;
END$$
DELIMITER ;

SET @emp = 178;

SELECT `first_name`, `salary`
FROM `employees`
WHERE `employee_id` = @emp;

SET SQL_SAFE_UPDATES = 0;

CALL `usp_raise_salary_by_id`(@emp);