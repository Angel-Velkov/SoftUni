package springdata.exercises.bookshopsystem.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdata.exercises.bookshopsystem.services.AuthorService;
import springdata.exercises.bookshopsystem.services.BookService;
import springdata.exercises.bookshopsystem.services.CategoryService;

import java.io.IOException;

@Component
@Slf4j
public class ConsoleRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seed();
    }

    private void seed() throws IOException {
        this.authorService.seedAuthor();
        this.bookService.seedBooks();
        this.categoryService.seedCategory();
    }
}
