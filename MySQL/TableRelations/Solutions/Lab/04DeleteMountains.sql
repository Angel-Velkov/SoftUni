USE `camp`;

DROP TABLE IF EXISTS `peaks`;
DROP TABLE IF EXISTS `mountains`;

CREATE TABLE `mountains` (
    `id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `peaks` (
	`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `mountain_id` INT NOT NULL,
    CONSTRAINT `fk_mountain_id` FOREIGN KEY (`mountain_id`)
		REFERENCES `mountains` (`id`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);