package dev.springforge.t2_04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 03: Entity Relationships & Custom Queries
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Add @ManyToOne relationship to Order and create custom queries.
 *
 * WHAT TO DO:
 * 1. Add @Entity, @Table, @Id, @GeneratedValue to Order
 * 2. Add @ManyToOne and @JoinColumn to the customer field
 * 3. Make OrderRepository extend JpaRepository<Order, Long>
 * 4. Add findByCustomerId and @Query findByMinTotal methods
 */
@DataJpaTest
@DisplayName("T2-04 Ex03: Relationships & Queries")
class Ex03_RelationshipsTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Customer alice;
    private Customer bob;

    @BeforeEach
    void setUp() {
        alice = entityManager.persist(new Customer("Alice", "alice@example.com", "555-0101"));
        bob = entityManager.persist(new Customer("Bob", "bob@example.com", "555-0102"));

        entityManager.persist(new Order(alice, "Laptop", new BigDecimal("1299.99")));
        entityManager.persist(new Order(alice, "Mouse", new BigDecimal("49.99")));
        entityManager.persist(new Order(bob, "Keyboard", new BigDecimal("89.99")));
        entityManager.flush();
    }

    @Test
    @DisplayName("Order has a reference to its Customer")
    void orderHasCustomerReference() {
        List<Order> orders = orderRepository.findAll();
        assertThat(orders).as("orders should exist").isNotEmpty();
        assertThat(orders.get(0).getCustomer()).as("order should reference a customer").isNotNull();
    }

    @Test
    @DisplayName("findByCustomerId returns orders for a specific customer")
    void findByCustomerIdFilters() {
        List<Order> aliceOrders = orderRepository.findByCustomerId(alice.getId());
        assertThat(aliceOrders).as("Alice should have 2 orders").hasSize(2);
        assertThat(aliceOrders).extracting(Order::getDescription)
                .as("Alice's order descriptions")
                .containsExactlyInAnyOrder("Laptop", "Mouse");
    }

    @Test
    @DisplayName("findByMinTotal returns orders above threshold")
    void findByMinTotalFilters() {
        List<Order> expensive = orderRepository.findByMinTotal(new BigDecimal("100.00"));
        assertThat(expensive).as("orders above $100").hasSize(1);
        assertThat(expensive.get(0).getDescription()).as("expensive order is Laptop").isEqualTo("Laptop");
    }
}
