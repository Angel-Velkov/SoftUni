SELECT REPLACE(`title`, 'The', '***') FROM `books`
WHERE (`title` , 1, 3) = 'The';