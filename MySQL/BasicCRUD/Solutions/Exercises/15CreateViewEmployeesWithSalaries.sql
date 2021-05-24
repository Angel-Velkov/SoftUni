USE `soft_uni`;

CREATE VIEW `v_employees_salaries` AS
	SELECT `first_name`, `last_name`, round(`salary`, 2)
	FROM `employees`;
    
SELECT * FROM `v_employees_salaries`;