package dev.springforge.t3_01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 03: Write Your Own T-SQL Migration
 *
 * YOUR TASKS:
 * Create a file: src/main/resources/db/mssql/V3__add_departments_table.sql
 *
 * The migration should:
 * 1. Create a 'departments' table with:
 *    - id: BIGINT IDENTITY(1,1) PRIMARY KEY
 *    - name: NVARCHAR(100) NOT NULL UNIQUE
 *    - budget: DECIMAL(15,2)
 *    - created_at: DATETIME2 NOT NULL DEFAULT GETUTCDATE()
 *
 * 2. Insert 3 seed departments:
 *    - 'Engineering', budget 500000.00
 *    - 'Marketing', budget 250000.00
 *    - 'Finance', budget 300000.00
 *
 * Remember: Use T-SQL syntax, not PostgreSQL!
 * - IDENTITY(1,1), not SERIAL
 * - NVARCHAR, not VARCHAR (for Unicode)
 * - DATETIME2 with GETUTCDATE(), not TIMESTAMP with NOW()
 *
 * REQUIRES: Docker running
 */
@SpringBootTest(classes = MssqlTestApp.class, properties = {
        "spring.flyway.enabled=true",
        "spring.flyway.locations=classpath:db/mssql",
        "spring.jpa.hibernate.ddl-auto=none"
})
@Import(MssqlTestcontainersConfig.class)
@DisplayName("T3-01 Ex03: Write Your Own MSSQL Migration")
class Ex03_WriteYourOwnMssqlMigrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("V3 migration should have been applied")
    void v3MigrationShouldExist() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM flyway_schema_history WHERE version = '3'",
                Integer.class);
        assertThat(count)
                .as("V3 migration should exist in flyway_schema_history")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Departments table should exist")
    void departmentsTableShouldExist() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'departments'",
                Integer.class);
        assertThat(count)
                .as("departments table should exist")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Departments table should have correct columns")
    void shouldHaveCorrectColumns() {
        Integer idCol = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'departments' AND COLUMN_NAME = 'id' AND DATA_TYPE = 'bigint'",
                Integer.class);
        assertThat(idCol).as("id column should be BIGINT").isEqualTo(1);

        Integer nameCol = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'departments' AND COLUMN_NAME = 'name' AND DATA_TYPE = 'nvarchar'",
                Integer.class);
        assertThat(nameCol).as("name column should be NVARCHAR").isEqualTo(1);

        Integer budgetCol = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'departments' AND COLUMN_NAME = 'budget' AND DATA_TYPE = 'decimal'",
                Integer.class);
        assertThat(budgetCol).as("budget column should be DECIMAL").isEqualTo(1);
    }

    @Test
    @DisplayName("Should have 3 seed departments")
    void shouldHaveSeedData() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM departments", Integer.class);
        assertThat(count)
                .as("Should have 3 seed departments")
                .isEqualTo(3);
    }

    @Test
    @DisplayName("Engineering department should have correct budget")
    void engineeringBudgetShouldBeCorrect() {
        var budget = jdbcTemplate.queryForObject(
                "SELECT budget FROM departments WHERE name = 'Engineering'",
                java.math.BigDecimal.class);
        assertThat(budget)
                .as("Engineering budget should be 500000.00")
                .isEqualByComparingTo(new java.math.BigDecimal("500000.00"));
    }
}
