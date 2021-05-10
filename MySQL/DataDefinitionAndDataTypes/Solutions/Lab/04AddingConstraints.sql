ALTER TABLE `products` 
ADD CONSTRAINT `fk_category_id`
  FOREIGN KEY (`category_id`)
  REFERENCES `gamebar`.`categories` (`id`)
  ON DELETE RESTRICT
  ON UPDATE NO ACTION;