package dev.springforge.t2_04;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Exercise 3: Order Repository with Custom Query
 *
 * This interface already extends JpaRepository and has method signatures.
 * The methods work once Order is a proper @Entity with relationships (Ex03).
 *
 * STUDY: Two query styles:
 * - findByCustomerId → derived query (Spring parses the method name)
 * - findByMinTotal → @Query with explicit JPQL
 *
 * YOUR TASK (after completing Ex03 entity annotations):
 * Verify these methods work by running the Ex03 tests.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    @Query("SELECT o FROM Order o WHERE o.total >= :minTotal")
    List<Order> findByMinTotal(@Param("minTotal") BigDecimal minTotal);
}
