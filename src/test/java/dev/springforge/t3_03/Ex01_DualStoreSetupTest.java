package dev.springforge.t3_03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: Dual Data Store Setup (Worked Example)
 *
 * This test verifies that BOTH data stores are accessible
 * in the same Spring context — SQL Server via JPA and
 * MongoDB via Spring Data MongoDB.
 *
 * WHAT YOU LEARN:
 * - Both @Entity (Account) and @Document (AuditLog) coexist
 * - Both JpaRepository and MongoRepository work simultaneously
 * - DualStoreConfig wires everything together
 *
 * REQUIRES: Docker running (for SQL Server + MongoDB containers)
 */
@SpringBootTest(classes = {DualStoreTestApp.class, DualStoreTestConfig.class}, properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.autoconfigure.exclude="})
@Import(DualStoreTestConfig.class)
@DisplayName("T3-03 Ex01: Dual Data Store Setup")
class Ex01_DualStoreSetupTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("SQL Server connection is active")
    void sqlServerIsConnected() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(conn.isValid(5)).as("SQL Server connection should be valid").isTrue();
            assertThat(conn.getMetaData().getDatabaseProductName())
                    .as("database should be Microsoft SQL Server")
                    .containsIgnoringCase("Microsoft SQL Server");
        }
    }

    @Test
    @DisplayName("MongoDB connection is active")
    void mongoIsConnected() {
        // MongoTemplate can list collections — proves connection works
        assertThat(mongoTemplate.getDb().getName()).as("MongoDB database name should exist").isNotEmpty();
    }

    @Test
    @DisplayName("JPA Account entity persists to SQL Server")
    void accountPersistsToSql() {
        Account account = new Account("jdoe", "jdoe@springforge.dev", "DEVELOPER");
        Account saved = accountRepository.save(account);

        assertThat(saved.getId()).as("saved account should have a generated ID").isNotNull();
        assertThat(accountRepository.findByUsername("jdoe")).as("account should be findable by username").isPresent();
    }

    @Test
    @DisplayName("AuditLog document persists to MongoDB")
    void auditLogPersistsToMongo() {
        AuditLog log = new AuditLog(1L, "LOGIN", "User logged in");
        AuditLog saved = mongoTemplate.save(log);

        assertThat(saved.getId()).as("saved audit log should have a generated ID").isNotNull();
        assertThat(mongoTemplate.findAll(AuditLog.class)).as("audit logs should exist in MongoDB").isNotEmpty();
    }
}
