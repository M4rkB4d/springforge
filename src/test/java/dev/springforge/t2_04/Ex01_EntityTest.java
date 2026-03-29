package dev.springforge.t2_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: JPA Entity
 *
 * DIFFICULTY: ★★☆☆☆
 *
 * OBJECTIVE: Annotate the Customer class as a JPA entity.
 *
 * WHAT TO DO:
 * 1. Add @Entity and @Table(name = "customers") to Customer
 * 2. Add @Id and @GeneratedValue(strategy = GenerationType.IDENTITY) to id
 * 3. Add @Column annotations to name and email
 */
@DataJpaTest
@DisplayName("T2-04 Ex01: JPA Entity")
class Ex01_EntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Customer entity can be persisted and has generated ID")
    void customerCanBePersisted() {
        Customer customer = new Customer("Alice", "alice@example.com", "555-0101");
        Customer saved = entityManager.persistFlushFind(customer);

        assertThat(saved.getId()).as("ID should be generated").isNotNull();
        assertThat(saved.getName()).as("name should be persisted").isEqualTo("Alice");
        assertThat(saved.getEmail()).as("email should be persisted").isEqualTo("alice@example.com");
    }

    @Test
    @DisplayName("Customer entity fields are mapped to correct columns")
    void customerFieldsAreMapped() {
        Customer customer = new Customer("Bob", "bob@example.com", "555-0102");
        Customer saved = entityManager.persistFlushFind(customer);

        // Clear the persistence context to force a fresh read from DB
        entityManager.clear();
        Customer found = entityManager.find(Customer.class, saved.getId());

        assertThat(found).as("customer should be found by ID").isNotNull();
        assertThat(found.getName()).as("name column mapping").isEqualTo("Bob");
        assertThat(found.getEmail()).as("email column mapping").isEqualTo("bob@example.com");
        assertThat(found.getPhone()).as("phone column mapping").isEqualTo("555-0102");
    }
}
