package springdata.exercises.bookshopsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springdata.exercises.bookshopsystem.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
