package dev.springforge.t2_04;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 2: Spring Data JPA Repository
 *
 * This interface already extends JpaRepository and has method signatures.
 * The methods work automatically once Customer is a proper @Entity (Ex01).
 *
 * STUDY: Look at the method names — Spring Data parses them into SQL queries.
 * - findByEmail → SELECT * FROM customers WHERE email = ?
 * - findByNameContainingIgnoreCase → SELECT * FROM customers WHERE LOWER(name) LIKE LOWER('%?%')
 *
 * YOUR TASK (after completing Ex01):
 * Verify these methods work by running the Ex02 tests.
 * Understand HOW Spring derives queries from method names.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByNameContainingIgnoreCase(String name);
}
