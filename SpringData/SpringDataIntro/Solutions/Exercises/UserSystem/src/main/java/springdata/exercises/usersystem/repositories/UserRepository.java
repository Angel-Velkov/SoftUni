package springdata.exercises.usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springdata.exercises.usersystem.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
