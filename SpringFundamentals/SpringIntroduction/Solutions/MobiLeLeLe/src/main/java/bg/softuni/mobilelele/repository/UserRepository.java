package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT (u) > 0 THEN true ELSE false END " +
            "FROM UserEntity u " +
            "JOIN u.roles r " +
            "WHERE u.username =:username " +
            "   AND r.role = 'ADMIN'")
    boolean isAdmin(String username);
}
