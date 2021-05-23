USE `minions`;

DROP TABLE IF EXISTS `people`;

CREATE TABLE `people` (
	`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(200) NOT NULL,
    `picture` BLOB,
    `height` FLOAT (2),
    `weight` FLOAT(2),
    `gender` CHAR(1) NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography` TEXT
);

INSERT INTO `people` (`name`, `picture`, `height`, `weight`, `gender`, `birthdate`)
VALUES
('Angel', 'achozavur', 1.81, 88, 'M','2001-06-13'),
('Ivan', 'venezeluelan president', 1.80, 69, 'M','2002-06-06'),
('Tihov', 'octapus', 1.88, 79, 'X','2002-04-01'),
('Hrisi', 'Stewie', 1.66, 58, 'M','2001-08-13'),
('Marto', 'The bearded lady', 1.82, 72, 'M', '2001-10-03')
;

SELECT * FROM `people`;