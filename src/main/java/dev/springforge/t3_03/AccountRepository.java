package dev.springforge.t3_03;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA Repository for Account — backed by SQL database.
 *
 * This extends JpaRepository (not MongoRepository).
 * Spring Data uses the domain annotation (@Entity vs @Document)
 * and the repository type to route to the correct data store.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
}
