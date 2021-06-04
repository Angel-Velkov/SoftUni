DROP DATABASE IF EXISTS `model`;

CREATE DATABASE `model`;
USE `model`;

DROP TABLES IF EXISTS `students`, `exams`, `students_exams`;

CREATE TABLE `students` (
	`student_id` INT PRIMARY KEY NOT NULL,
    `name` VARCHAR(20) NOT NULL
);

CREATE TABLE `exams` (
	`exam_id` INT PRIMARY KEY NOT NULL,
    `name` VARCHAR(30) NOT NULL
);

CREATE TABLE `students_exams` (
	`student_id` INT NOT NULL,
    `exam_id` INT NOT NULL,
    CONSTRAINT `pk_students_exams`
		PRIMARY KEY (`student_id`, `exam_id`),
    CONSTRAINT `fk_students_exams_sudents`
		FOREIGN KEY (`student_id`)
		REFERENCES `students` (`student_id`),
    CONSTRAINT `fk_students_exams_exams`
		FOREIGN KEY (`exam_id`)
        REFERENCES `exams` (`exam_id`)
);

INSERT INTO `students`
VALUES
	(1, 'Mia'),
    (2, 'Tom'),
    (3, 'Rom')
;

INSERT INTO `exams`
VALUES
	(101, 'Spring MVC'),
    (102, 'Neo4j'),
    (103, 'Oracle11g')
;

INSERT INTO `students_exams`
VALUES
	(1, 101),
    (1, 102),
    (2, 101),
    (3, 103),
    (2, 102),
    (2, 103)
;

SELECT `s`.`student_id`, `s`.`name` AS 'first_name',
	`e`.`exam_id`, `e`.`name` AS 'exam'
FROM `students` AS `s`
JOIN `students_exams` AS `se`ON `s`.`student_id` = `se`.`student_id`
JOIN `exams` AS `e` ON `se`.`exam_id` = `e`.`exam_id`
ORDER BY `s`.`student_id`, `e`.`exam_id`;