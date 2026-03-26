package dev.springforge.t3_03;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Exercise 2: MongoDB Repository for AuditLog
 *
 * YOUR TASKS:
 * 1. Add a derived query method: findByAccountId(Long accountId)
 *    returning List<AuditLog>
 * 2. Add a derived query method: findByAction(String action)
 *    returning List<AuditLog>
 *
 * Notice this extends MongoRepository (not JpaRepository).
 * Spring Data routes queries to MongoDB automatically because
 * AuditLog is annotated with @Document.
 */
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

    // These query methods are auto-implemented by Spring Data MongoDB
    // from the method name. They work ONLY when AuditLog has
    // @Document annotation and DualStoreConfig has @EnableMongoRepositories.
    List<AuditLog> findByAccountId(Long accountId);

    List<AuditLog> findByAction(String action);
}
