USE `minions`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(26) NULL,
	`profile_picture` BLOB,
	`last_login_time` DATETIME NOT NULL,
	`is_deleted` TINYINT NOT NULL
);

INSERT INTO `users` (`username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES
('acho007', '123456', 'achozavur', '2021-05-23 01:36:59', false),
('ziruzavar', 'valhala', 'venezeluelan president', '2021-05-23 01:41:24', false),
('spectraphantom', 'ROFL', 'octapus', '2011-02-13 14:26:19', true),
('hriskoo', 'astraterminator', 'Stewie', '2081-10-24 04:20:00', false),
('misomisev', 'KalII', 'The bearded lady', '2012-05-23 03:06:09', true)
;