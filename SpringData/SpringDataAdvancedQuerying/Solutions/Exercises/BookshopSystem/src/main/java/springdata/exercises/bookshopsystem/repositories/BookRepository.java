package springdata.exercises.bookshopsystem.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springdata.exercises.bookshopsystem.models.Book;
import springdata.exercises.bookshopsystem.models.enums.AgeRestriction;
import springdata.exercises.bookshopsystem.models.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByReleaseDateAfter(LocalDate releaseDate);

    @Query("SELECT b FROM Book AS b WHERE b.author.id = :authorId")
    List<Book> getBooksByAuthorOrderedByReleaseDateThenByTitle(@Param("authorId") Long authorId);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesBefore(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate lowerBound, LocalDate upperBound);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByTitleContaining(String substring);
}
