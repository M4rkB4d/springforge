package dev.springforge.t3_03;

// Mongock imports — commented out until Boot 4.0 compatible artifact ships
// import io.mongock.api.annotations.ChangeUnit;
// import io.mongock.api.annotations.Execution;
// import io.mongock.api.annotations.RollbackExecution;
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
 *
 * STATUS: DISABLED — Mongock 5.5.0 (mongock-springboot-v3) is incompatible with
 * Spring Boot 4.0 / Spring Data 5.x. Re-enable when Mongock ships a Boot 4 artifact.
 * Uncomment the annotations below and the imports above when ready.
 */
// @ChangeUnit(id = "001-initial-audit-log", order = "001", author = "springforge")
public class InitialDataChangeset {

    // @Execution
    public void execute(MongoTemplate mongoTemplate) {
        mongoTemplate.save(new AuditLog(1L, "SYSTEM_INIT", "Initial system setup via Mongock migration"));
    }

    // @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.remove(
                org.springframework.data.mongodb.core.query.Query.query(
                        org.springframework.data.mongodb.core.query.Criteria.where("action").is("SYSTEM_INIT")),
                AuditLog.class);
    }
}
