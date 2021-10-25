package springdata.exercises.bookshopsystem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdata.exercises.bookshopsystem.models.Author;
import springdata.exercises.bookshopsystem.repositories.AuthorRepository;
import springdata.exercises.bookshopsystem.services.AuthorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthor() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .forEach(row -> {
                    String[] fullName = row.split("\\s+");

                    Author author = new Author(fullName[0], fullName[1]);

                    this.authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        long authorRepositoryCount = this.authorRepository.count();

        if (authorRepositoryCount == 0) {
            throw new IllegalStateException("No authors in the AuthorRepository");
        }

        long randomId = ThreadLocalRandom.current().nextLong(authorRepositoryCount) + 1;

        return authorRepository.getById(randomId);
    }
}
