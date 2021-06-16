USE `soft_uni`;

DROP VIEW IF EXISTS `v_employees_hired_after_2000`;

CREATE VIEW `v_employees_hired_after_2000` AS
	SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE YEAR(`hire_date`) > 2000
;

SELECT * FROM `v_employees_hired_after_2000`;