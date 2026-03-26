package dev.springforge.t3_03;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Exercise 3: Mongock Change Unit — MongoDB Migration
 *
 * YOUR TASKS:
 * 1. In the execute() method, create and save an AuditLog document:
 *    - accountId: 1L
 *    - action: "SYSTEM_INIT"
 *    - details: "Initial system setup via Mongock migration"
 *    Use: mongoTemplate.save(new AuditLog(...))
 *
 * 2. In the rollback() method, remove the document:
 *    Use: mongoTemplate.remove(
 *        Query.query(Criteria.where("action").is("SYSTEM_INIT")),
 *        AuditLog.class)
 *
 * WHY MONGOCK:
 * Flyway handles SQL migrations. Mongock is the equivalent for MongoDB.
 * Each @ChangeUnit runs exactly once, tracked in a mongockChangeLog
 * collection. This gives you versioned, repeatable MongoDB schema changes.
 *
 * NOTE: In Mongock v5, @ChangeUnit replaces the deprecated
 * @ChangeLog/@ChangeSet from v4. Always use @ChangeUnit for new code.
 */
@ChangeUnit(id = "001-initial-audit-log", order = "001", author = "springforge")
public class InitialDataChangeset {

    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        // TODO: Create and save an initial AuditLog document
        throw new UnsupportedOperationException("Implement the Mongock migration");
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        // TODO: Remove the initial AuditLog document
        throw new UnsupportedOperationException("Implement the rollback");
    }
}
