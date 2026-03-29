package dev.springforge.t2_06;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import dev.springforge.t2_05.TestcontainersConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: Flyway Migrations (Worked Example)
 *
 * DIFFICULTY: ★★☆☆☆
 *
 * These tests verify that Flyway migrations ran successfully.
 * Study how Flyway integrates with Spring Boot — migrations run
 * automatically before the application context is ready.
 *
 * REQUIRES: Docker running (uses Testcontainers for PostgreSQL)
 */
@SpringBootTest(classes = FlywayTestApp.class, properties = {
        "spring.flyway.enabled=true",
        "spring.jpa.hibernate.ddl-auto=none"
})
@Import(TestcontainersConfig.class)
@DisplayName("T2-06 Ex01: Flyway Migrations")
class Ex01_FlywayMigrationTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Flyway is auto-configured and migrations have run")
    void flywayIsConfigured() {
        assertThat(flyway).as("Flyway should be auto-configured").isNotNull();
        assertThat(flyway.info().applied().length).as("at least 2 migrations applied").isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("V1 migration created the articles table")
    void articlesTableExists() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'articles'",
                Integer.class);
        assertThat(count).as("articles table should exist").isEqualTo(1);
    }

    @Test
    @DisplayName("V2 migration added the category column")
    void categoryColumnExists() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.columns WHERE table_name = 'articles' AND column_name = 'category'",
                Integer.class);
        assertThat(count).as("category column should exist").isEqualTo(1);
    }
}
