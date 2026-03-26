package dev.springforge.t2_06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import dev.springforge.t2_05.TestcontainersConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: Write Your Own Migration
 *
 * DIFFICULTY: ★★★☆☆
 *
 * YOUR TASK: Create V3__add_tags_table.sql in src/main/resources/db/migration/
 *
 * The migration should create:
 * 1. A 'tags' table (id BIGSERIAL PK, name VARCHAR(50) NOT NULL UNIQUE)
 * 2. An 'article_tags' join table (article_id, tag_id as composite PK with FKs)
 *
 * These tests verify your migration ran correctly.
 *
 * REQUIRES: Docker running
 */
@SpringBootTest
@Import(TestcontainersConfig.class)
@DisplayName("T2-06 Ex02: Write Your Own Migration")
class Ex02_WriteYourOwnMigrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("V3 migration created the tags table")
    void tagsTableExists() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'tags'",
                Integer.class);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("Tags table has correct columns")
    void tagsTableHasCorrectColumns() {
        Integer nameColumn = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.columns WHERE table_name = 'tags' AND column_name = 'name'",
                Integer.class);
        assertThat(nameColumn).isEqualTo(1);
    }

    @Test
    @DisplayName("V3 migration created the article_tags join table")
    void articleTagsTableExists() {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'article_tags'",
                Integer.class);
        assertThat(count).isEqualTo(1);
    }
}
