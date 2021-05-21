ALTER TABLE `peaks`
DROP CONSTRAINT `fk_mountain_id`;

ALTER TABLE `peaks`
ADD  CONSTRAINT `fk_mountain_id` FOREIGN KEY (`mountain_id`)
		REFERENCES `mountains` (`id`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION