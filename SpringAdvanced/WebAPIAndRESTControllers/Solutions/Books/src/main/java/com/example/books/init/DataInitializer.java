package com.example.books.init;

import com.example.books.model.entity.AuthorEntity;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.bookRepository.count() == 0 && this.authorRepository.count() == 0) {
            initAuthor("Николай Хайтов",
                    "Диви Разкази");

            initAuthor("Димиртър Димов",
                    "Тютюн");

            initAuthor("Елин Пелин",
                    "Пижо и Пендо",
                    "Ян Бибиян на луната",
                    "Под манастирската лоза");

            initAuthor("Иван Вазов",
                    "Пряпорец и Гусла",
                    "Под Игото",
                    "Тъгите на България");

            initAuthor("Йордан Йовков",
                    "Старопланински легенди",
                    "Чифликът край границата");
        }
    }

    private void initAuthor(String authorName, String... bookTitles) {
        AuthorEntity author = new AuthorEntity(authorName);

        for (String bookTitle : bookTitles) {
            BookEntity book = new BookEntity(
                    bookTitle,
                    UUID.randomUUID().toString(),
                    author
            );

            author.addBook(book);
        }

        this.authorRepository.save(author);
    }
}
