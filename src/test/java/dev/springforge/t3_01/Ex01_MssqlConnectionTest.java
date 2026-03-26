package dev.springforge.t3_01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: Connect to SQL Server via Testcontainers (Worked Example)
 *
 * This test demonstrates:
 * - MSSQLServerContainer with .acceptLicense()
 * - @ServiceConnection auto-wiring the datasource
 * - Verifying the connection is to SQL Server (not H2)
 *
 * REQUIRES: Docker running
 */
@SpringBootTest(properties = {
        "spring.flyway.enabled=true",
        "spring.flyway.locations=classpath:db/mssql",
        "spring.jpa.hibernate.ddl-auto=validate"
})
@Import(MssqlTestcontainersConfig.class)
@DisplayName("T3-01 Ex01: MSSQL Connection")
class Ex01_MssqlConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Should connect to SQL Server (not H2)")
    void shouldConnectToSqlServer() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            assertThat(meta.getDatabaseProductName())
                    .as("Should be connected to Microsoft SQL Server, not H2")
                    .containsIgnoringCase("Microsoft SQL Server");
        }
    }

    @Test
    @DisplayName("Should have run Flyway migrations")
    void shouldHaveRunFlywayMigrations() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM flyway_schema_history", Integer.class);
        assertThat(count)
                .as("Flyway should have run at least 2 migrations (V1, V2)")
                .isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Employees table should exist with correct columns")
    void employeesTableShouldExist() {
        Integer columnCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'employees'",
                Integer.class);
        assertThat(columnCount)
                .as("employees table should have columns from V1 + V2 migrations")
                .isGreaterThanOrEqualTo(8);
    }
}
