package hiberspring.repository;

import hiberspring.domain.dtos.ProductiveEmployeeDto;
import hiberspring.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByCard_Number(String cardNumber);

    @Query("SELECT new hiberspring.domain.dtos.ProductiveEmployeeDto(" +
            "(concat(e.firstName, ' ', e.lastName)), e.position ,c.number) " +
            "FROM Employee AS e" +
            "   JOIN e.card AS c " +
            "   JOIN e.branch AS b " +
            "WHERE SIZE(b.products) > 0 " +
            "ORDER BY concat(e.firstName, ' ', e.lastName), LENGTH(e.position) DESC")
    List<ProductiveEmployeeDto> findAllEmployeesInBranchWithAtLeastOneProduct();
}
