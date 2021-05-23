USE `minions`;

ALTER TABLE `users`
MODIFY COLUMN `last_login_time` DATETIME NOT NULL DEFAULT NOW();
