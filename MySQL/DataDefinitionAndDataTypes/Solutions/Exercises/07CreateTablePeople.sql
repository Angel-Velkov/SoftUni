USE `missions`;

CREATE TABLE `people` (
	`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(200) NOT NULL,
    `picture` BLOB,
    `height` FLOAT(2),
    `weight` FLOAT(2),
    `gender` CHAR(1) NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography` TEXT
);