USE `restaurant`;

SELECT `category_id`,
ROUND(AVG(`price`), 2),
ROUND(MIN(`price`), 2),
ROUND(MAX(`price`), 2)
FROM `products`
GROUP BY `category_id`;
 