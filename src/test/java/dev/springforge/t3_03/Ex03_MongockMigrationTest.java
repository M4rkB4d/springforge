package dev.springforge.t3_03;

import io.mongock.runner.springboot.EnableMongock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 03: Mongock Migration
 *
 * YOUR TASK:
 * In InitialDataChangeset.java, implement the execute() method to:
 *   1. Create an AuditLog with accountId=1L, action="SYSTEM_INIT",
 *      details="Initial system setup via Mongock migration"
 *   2. Save it using mongoTemplate.save(...)
 *
 * Also implement the rollback() method to remove the document.
 *
 * WHY THIS MATTERS:
 * In production, you use Flyway for SQL migrations and Mongock for
 * MongoDB migrations. Both run at startup, both are versioned,
 * both ensure each migration runs exactly once.
 *
 * REQUIRES: Docker running
 */
@SpringBootTest(properties = {
        "mongock.enabled=true",
        "mongock.migration-scan-package=dev.springforge.t3_03"
})
@Import(DualStoreTestConfig.class)
@EnableMongock
@DisplayName("T3-03 Ex03: Mongock Migration")
class Ex03_MongockMigrationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("Mongock migration created the initial audit log")
    void migrationCreatedInitialData() {
        Query query = Query.query(Criteria.where("action").is("SYSTEM_INIT"));
        List<AuditLog> results = mongoTemplate.find(query, AuditLog.class);

        assertThat(results)
                .hasSize(1)
                .first()
                .satisfies(log -> {
                    assertThat(log.getAccountId()).isEqualTo(1L);
                    assertThat(log.getAction()).isEqualTo("SYSTEM_INIT");
                    assertThat(log.getDetails()).contains("Mongock migration");
                });
    }

    @Test
    @DisplayName("Mongock tracked the changeset in its changelog collection")
    void mongockChangelogExists() {
        // Mongock stores execution history in mongockChangeLog collection
        boolean changeLogExists = mongoTemplate.getDb()
                .listCollectionNames()
                .into(new java.util.ArrayList<>())
                .stream()
                .anyMatch(name -> name.contains("mongockChangeLog"));

        assertThat(changeLogExists)
                .as("Mongock should create a changelog collection to track migrations")
                .isTrue();
    }
}
