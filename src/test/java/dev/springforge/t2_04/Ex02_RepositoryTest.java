package dev.springforge.t2_04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: Spring Data Repository
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Create a Spring Data JPA repository with derived query methods.
 *
 * WHAT TO DO:
 * 1. Make CustomerRepository extend JpaRepository<Customer, Long>
 * 2. Add findByEmail(String email) → Optional<Customer>
 * 3. Add findByNameContainingIgnoreCase(String name) → List<Customer>
 */
@DataJpaTest
@DisplayName("T2-04 Ex02: Spring Data Repository")
class Ex02_RepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.persist(new Customer("Alice Johnson", "alice@example.com", "555-0101"));
        entityManager.persist(new Customer("Bob Smith", "bob@example.com", "555-0102"));
        entityManager.persist(new Customer("Charlie Johnson", "charlie@example.com", "555-0103"));
        entityManager.flush();
    }

    @Test
    @DisplayName("findAll returns all customers")
    void findAllReturnsAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).as("findAll should return all 3 customers").hasSize(3);
    }

    @Test
    @DisplayName("findByEmail returns matching customer")
    void findByEmailReturnsCustomer() {
        Optional<Customer> customer = customerRepository.findByEmail("alice@example.com");
        assertThat(customer).as("should find alice by email").isPresent();
        assertThat(customer.get().getName()).as("found customer name").isEqualTo("Alice Johnson");
    }

    @Test
    @DisplayName("findByEmail returns empty for non-existent email")
    void findByEmailReturnsEmptyForMissing() {
        Optional<Customer> customer = customerRepository.findByEmail("nobody@example.com");
        assertThat(customer).as("non-existent email returns empty").isEmpty();
    }

    @Test
    @DisplayName("findByNameContainingIgnoreCase finds partial matches")
    void findByNameContainingIgnoreCase() {
        List<Customer> johnsons = customerRepository.findByNameContainingIgnoreCase("johnson");
        assertThat(johnsons).as("should find 2 Johnsons").hasSize(2);
        assertThat(johnsons).extracting(Customer::getName)
                .as("Johnson family members")
                .containsExactlyInAnyOrder("Alice Johnson", "Charlie Johnson");
    }
}
