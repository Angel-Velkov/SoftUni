package springdata.exercises.bookshopsystem.services;

import springdata.exercises.bookshopsystem.models.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthor() throws IOException;

    Author getRandomAuthor();
}
