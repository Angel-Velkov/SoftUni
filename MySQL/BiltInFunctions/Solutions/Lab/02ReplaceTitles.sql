SELECT REPLACE(`title`, 'The', '***') FROM `books`
WHERE SUBSTRING(`title` FROM 1 FOR 3) = 'The';