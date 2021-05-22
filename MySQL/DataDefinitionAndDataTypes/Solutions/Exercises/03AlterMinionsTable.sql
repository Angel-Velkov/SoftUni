USE `minions`;

# Data preparation
ALTER TABLE `towns`
CHANGE `town_id` `id` INT AUTO_INCREMENT NOT NULL;

ALTER TABLE `minions`
ADD `town_id` INT NOT NULL,
ADD CONSTRAINT `fk_minions_towns`
	FOREIGN KEY (`town_id`)
	REFERENCES `towns` (`id`);