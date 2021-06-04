DROP DATABASE IF EXISTS `model`;

CREATE DATABASE `model`;
USE `model`;

DROP TABLE IF EXISTS `teachers`;

CREATE TABLE `teachers` (
	`teacher_id` INT PRIMARY KEY NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `menager_id` INT
);

INSERT INTO `teachers`
VALUES
	(101, 'Jhon', NULL),
    (102, 'Maya', 106),
    (103, 'Silvia', 106),
    (104, 'Ted', 105),
    (105, 'Mark', 101),
    (106, 'Greta', 101)
;

ALTER TABLE `teachers`
ADD CONSTRAINT `fk_tachers_teachers`
	FOREIGN KEY (`menager_id`)
	REFERENCES `teachers`(`teacher_id`);

SELECT `t`.`teacher_id`, `t`.`name`,
	`t`.`menager_id`, `m`.`name` AS 'menager'
FROM `teachers` AS `t`
LEFT JOIN `teachers` AS `m` ON `t`.`menager_id` = `m`.`teacher_id`;