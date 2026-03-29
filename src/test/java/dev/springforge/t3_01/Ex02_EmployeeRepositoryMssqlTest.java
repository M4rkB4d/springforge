package dev.springforge.t3_01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: JPA Repository with Azure SQL
 *
 * YOUR TASKS:
 * 1. Complete the Employee entity annotations (@Table, @Column constraints)
 * 2. Run these tests — they verify your entity works with MSSQL via Flyway
 *
 * These tests run against a real SQL Server instance (Testcontainers).
 * The schema is created by Flyway migrations, not Hibernate ddl-auto.
 *
 * REQUIRES: Docker running
 */
@SpringBootTest(classes = MssqlTestApp.class, properties = {
        "spring.flyway.enabled=true",
        "spring.flyway.locations=classpath:db/mssql",
        "spring.jpa.hibernate.ddl-auto=none"
})
@Import(MssqlTestcontainersConfig.class)
@DisplayName("T3-01 Ex02: Employee Repository on MSSQL")
class Ex02_EmployeeRepositoryMssqlTest {

    @Autowired
    private EmployeeRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Should save and retrieve an employee")
    void shouldSaveAndRetrieve() {
        Employee emp = new Employee("Alice", "Smith", "alice@springforge.dev");
        emp.setDepartment("Engineering");
        emp.setSalary(new BigDecimal("95000.00"));
        emp.setHireDate(LocalDate.of(2024, 1, 15));

        Employee saved = repository.save(emp);
        assertThat(saved.getId()).isNotNull();

        Optional<Employee> found = repository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("Alice");
        assertThat(found.get().getSalary()).isEqualByComparingTo(new BigDecimal("95000.00"));
    }

    @Test
    @DisplayName("Should find employee by email")
    void shouldFindByEmail() {
        Employee emp = new Employee("Bob", "Jones", "bob@springforge.dev");
        repository.save(emp);

        Optional<Employee> found = repository.findByEmail("bob@springforge.dev");
        assertThat(found).isPresent();
        assertThat(found.get().getLastName()).isEqualTo("Jones");
    }

    @Test
    @DisplayName("Should find employees by department")
    void shouldFindByDepartment() {
        Employee e1 = new Employee("Alice", "Smith", "alice@springforge.dev");
        e1.setDepartment("Engineering");
        Employee e2 = new Employee("Bob", "Jones", "bob@springforge.dev");
        e2.setDepartment("Engineering");
        Employee e3 = new Employee("Carol", "Lee", "carol@springforge.dev");
        e3.setDepartment("Marketing");

        repository.saveAll(List.of(e1, e2, e3));

        List<Employee> engineers = repository.findByDepartment("Engineering");
        assertThat(engineers).hasSize(2);
    }

    @Test
    @DisplayName("Should find active employees with minimum salary")
    void shouldFindActiveWithMinSalary() {
        Employee e1 = new Employee("Alice", "Smith", "alice@springforge.dev");
        e1.setSalary(new BigDecimal("120000.00"));
        e1.setActive(true);

        Employee e2 = new Employee("Bob", "Jones", "bob@springforge.dev");
        e2.setSalary(new BigDecimal("80000.00"));
        e2.setActive(true);

        Employee e3 = new Employee("Carol", "Lee", "carol@springforge.dev");
        e3.setSalary(new BigDecimal("150000.00"));
        e3.setActive(false);

        repository.saveAll(List.of(e1, e2, e3));

        List<Employee> result = repository.findActiveWithMinSalary(new BigDecimal("100000.00"));
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getFirstName()).isEqualTo("Alice");
    }
}
