package springdata.exercises.bookshopsystem.services;

import springdata.exercises.bookshopsystem.models.Author;
import springdata.exercises.bookshopsystem.models.Book;
import springdata.exercises.bookshopsystem.models.enums.AgeRestriction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<Book> getBooksByAuthorOrderedByReleaseDateThenByTitle(Author author);

    List<String> findAllBookTitlesByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllGoldBookTitlesWithCopiesLessThan5000();

    List<String> findAllBooksWithPriceNotBetween5And40();

    List<String> findAllBookTitlesThatHaveNotBeenReleasedInYear(int year);

    List<Book> findAllBooksByReleaseDateBefore(LocalDate releaseDate);

    List<String> findAllBookTitlesWitchContains(String substring);


}
