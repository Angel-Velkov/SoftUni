USE `minions`;

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY (`id`);

ALTER TABLE `users`
ADD CONSTRAINT `uq_username`
UNIQUE (`username`);