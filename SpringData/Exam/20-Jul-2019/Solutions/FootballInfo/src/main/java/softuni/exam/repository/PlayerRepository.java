package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.dto.PlayerInfoDto;
import softuni.exam.domain.dto.PlayerViewDto;
import softuni.exam.domain.entities.Player;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<PlayerViewDto> findAllByTeam_Name(String teamName);

    @Query("SELECT new softuni.exam.domain.dto.PlayerInfoDto(" +
            "p.firstName, p.lastName, p.number, p.salary, t.name) " +
            "FROM Player AS p " +
            "   JOIN p.team AS t " +
            "ORDER BY p.salary DESC")
    List<PlayerInfoDto> findAllWithSalaryBiggerThanOrderedBySalaryDesc(BigDecimal salary);
}
