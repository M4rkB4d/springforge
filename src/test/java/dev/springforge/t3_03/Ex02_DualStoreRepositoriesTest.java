package dev.springforge.t3_03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: Dual Store Repositories
 *
 * YOUR TASKS:
 * 1. In AuditLog.java: Add @Document and @Field annotations
 * 2. In DualStoreConfig.java: Add @EnableJpaRepositories and @EnableMongoRepositories
 * 3. In AuditLogRepository.java: Add findByAccountId and findByAction query methods
 *
 * This exercise tests that both repositories work correctly
 * and route to the right data store automatically.
 *
 * REQUIRES: Docker running
 */
@SpringBootTest(classes = {DualStoreTestApp.class, DualStoreTestConfig.class}, properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.autoconfigure.exclude="})
@Import(DualStoreTestConfig.class)
@DisplayName("T3-03 Ex02: Dual Store Repositories")
class Ex02_DualStoreRepositoriesTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
        auditLogRepository.deleteAll();
    }

    @Test
    @DisplayName("JPA and Mongo repositories save to different stores")
    void repositoriesUseDifferentStores() {
        // Save to SQL via JPA
        Account account = accountRepository.save(
                new Account("admin", "admin@springforge.dev", "ADMIN"));

        // Save to MongoDB
        AuditLog log = auditLogRepository.save(
                new AuditLog(account.getId(), "ACCOUNT_CREATED", "Admin account created"));

        // Verify JPA store
        assertThat(accountRepository.count()).as("JPA store should have 1 account").isEqualTo(1);
        assertThat(accountRepository.findByUsername("admin")).as("admin account should be findable").isPresent();

        // Verify MongoDB store
        assertThat(auditLogRepository.count()).as("Mongo store should have 1 audit log").isEqualTo(1);
        assertThat(auditLogRepository.findByAccountId(account.getId()))
                .as("should find audit log for account")
                .hasSize(1)
                .first()
                .satisfies(entry -> {
                    assertThat(entry.getAction()).as("action should be ACCOUNT_CREATED").isEqualTo("ACCOUNT_CREATED");
                    assertThat(entry.getTimestamp()).as("timestamp should be auto-set").isNotNull();
                });
    }

    @Test
    @DisplayName("findByAction returns matching audit logs")
    void findByActionWorks() {
        auditLogRepository.save(new AuditLog(1L, "LOGIN", "User 1 login"));
        auditLogRepository.save(new AuditLog(2L, "LOGIN", "User 2 login"));
        auditLogRepository.save(new AuditLog(1L, "LOGOUT", "User 1 logout"));

        List<AuditLog> logins = auditLogRepository.findByAction("LOGIN");
        assertThat(logins).as("should find 2 LOGIN entries").hasSize(2);

        List<AuditLog> logouts = auditLogRepository.findByAction("LOGOUT");
        assertThat(logouts).as("should find 1 LOGOUT entry").hasSize(1);
    }

    @Test
    @DisplayName("Cross-store query — find audit logs for a specific account")
    void crossStoreQuery() {
        // Create two accounts in SQL
        Account alice = accountRepository.save(
                new Account("alice", "alice@springforge.dev", "DEVELOPER"));
        Account bob = accountRepository.save(
                new Account("bob", "bob@springforge.dev", "DEVELOPER"));

        // Create audit logs in MongoDB
        auditLogRepository.save(new AuditLog(alice.getId(), "LOGIN", "Alice logged in"));
        auditLogRepository.save(new AuditLog(alice.getId(), "UPDATE_PROFILE", "Alice updated profile"));
        auditLogRepository.save(new AuditLog(bob.getId(), "LOGIN", "Bob logged in"));

        // Cross-store: look up account, then query audit logs
        Account foundAlice = accountRepository.findByUsername("alice").orElseThrow();
        List<AuditLog> aliceLogs = auditLogRepository.findByAccountId(foundAlice.getId());

        assertThat(aliceLogs).as("Alice should have 2 audit logs").hasSize(2);
        assertThat(aliceLogs).extracting(AuditLog::getAction)
                .as("Alice's actions should be LOGIN and UPDATE_PROFILE")
                .containsExactlyInAnyOrder("LOGIN", "UPDATE_PROFILE");
    }
}
