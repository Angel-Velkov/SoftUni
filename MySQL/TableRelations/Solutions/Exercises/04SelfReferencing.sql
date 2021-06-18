DROP DATABASE IF EXISTS `model`;

CREATE DATABASE `model`;
USE `model`;

CREATE TABLE `teachers` (
	`teacher_id` INT PRIMARY KEY NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `manager_id` INT
);

INSERT INTO `teachers`
VALUES
	(101, 'John', NULL),
    (102, 'Maya', 106),
    (103, 'Silvia', 106),
    (104, 'Ted', 105),
    (105, 'Mark', 101),
    (106, 'Greta', 101)
;

ALTER TABLE `teachers`
ADD CONSTRAINT `fk_tachers_teachers`
	FOREIGN KEY (`manager_id`)
	REFERENCES `teachers`(`teacher_id`);

SELECT `t`.`teacher_id`, `t`.`name`,
	`t`.`manager_id`
FROM `teachers` AS `t`
LEFT JOIN `teachers` AS `m` ON `t`.`manager_id` = `m`.`teacher_id`;