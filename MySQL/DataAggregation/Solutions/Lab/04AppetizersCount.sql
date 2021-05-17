USE `restaurant`;

SELECT COUNT(*) AS `Count` FROM `products`
WHERE `category_id` = 2 And `price` > 8;