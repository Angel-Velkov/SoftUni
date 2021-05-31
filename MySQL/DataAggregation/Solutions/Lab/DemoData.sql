USE `restaurant`;

ALTER TABLE `products` 
ADD COLUMN `country` VARCHAR(45) NULL AFTER `price`;

UPDATE `products` SET `country` = 'Itally' WHERE (`id` = '1');
UPDATE `products` SET `country` = 'France' WHERE (`id` = '2');
UPDATE `products` SET `country` = 'France' WHERE (`id` = '6');
UPDATE `products` SET `country` = 'France' WHERE (`id` = '12');
UPDATE `products` SET `country` = 'France' WHERE (`id` = '11');
UPDATE `products` SET `country` = 'Itally' WHERE (`id` = '4');
UPDATE `products` SET `country` = 'Itally' WHERE (`id` = '5');
UPDATE `products` SET `country` = 'Itally' WHERE (`id` = '14');
INSERT INTO `products` (`id`, `name`, `category_id`, `price`, `country`) VALUES ('16', 'Spaghetti', '4', '7.8', 'Itally');
UPDATE `products` SET `country` = 'Germany' WHERE (`id` = '3');
UPDATE `products` SET `country` = 'Germany' WHERE (`id` = '7');
UPDATE `products` SET `country` = 'Germany' WHERE (`id` = '8');
UPDATE `products` SET `country` = 'Germany' WHERE (`id` = '10');
INSERT INTO `products` (`id`, `name`, `category_id`, `price`, `country`) VALUES ('17', 'Potatoes with Cream', '4', '6.9', 'Germany');
UPDATE `products` SET `country` = 'Turkey' WHERE (`id` = '9');
UPDATE `products` SET `country` = 'Turkey' WHERE (`id` = '13');
UPDATE `products` SET `country` = 'Turkey' WHERE (`id` = '15');

ALTER TABLE `restaurant`.`employees` 
ADD COLUMN `job` VARCHAR(45) NOT NULL AFTER `department_id`;

UPDATE `restaurant`.`employees` SET `job` = 'Cheff' WHERE (`id` = '2');
UPDATE `restaurant`.`employees` SET `job` = 'Cheff' WHERE (`id` = '1');
UPDATE `restaurant`.`employees` SET `job` = 'Manager' WHERE (`id` = '3');
UPDATE `restaurant`.`employees` SET `job` = 'Cheff' WHERE (`id` = '4');
UPDATE `restaurant`.`employees` SET `job` = 'Cheff' WHERE (`id` = '5');
UPDATE `restaurant`.`employees` SET `job` = 'Manager' WHERE (`id` = '6');
UPDATE `restaurant`.`employees` SET `job` = 'Waiter' WHERE (`id` = '7');
UPDATE `restaurant`.`employees` SET `job` = 'Waiter' WHERE (`id` = '8');
UPDATE `restaurant`.`employees` SET `job` = 'Waiter' WHERE (`id` = '9');

