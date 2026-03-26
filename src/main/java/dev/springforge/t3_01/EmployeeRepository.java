package dev.springforge.t3_01;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartment(String department);

    List<Employee> findByActiveTrue();

    @Query("SELECT e FROM Employee e WHERE e.salary >= :minSalary AND e.active = true")
    List<Employee> findActiveWithMinSalary(BigDecimal minSalary);
}
