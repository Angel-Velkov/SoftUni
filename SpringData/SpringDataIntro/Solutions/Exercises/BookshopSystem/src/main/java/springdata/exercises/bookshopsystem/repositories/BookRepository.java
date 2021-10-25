package springdata.exercises.bookshopsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springdata.exercises.bookshopsystem.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
