package dev.springforge.t2_05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: Write Your Own Container Tests
 *
 * DIFFICULTY: ★★★☆☆
 *
 * The setup is provided. YOUR TASK: write the test assertions.
 * These tests run against real PostgreSQL via Testcontainers.
 *
 * WHAT TO DO:
 * 1. Complete each test method by replacing the throw
 * 2. Use the patterns from Ex01 as a reference
 */
@DataJpaTest
@Import(TestcontainersConfig.class)
@DisplayName("T2-05 Ex02: Write Your Own Container Tests")
class Ex02_WriteYourOwnContainerTestTest {

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();
        articleRepository.save(new Article("JPA Basics", "Intro to JPA", "Charlie", true));
        articleRepository.save(new Article("Advanced JPA", "Deep dive", "Charlie", false));
        articleRepository.save(new Article("Spring Security", "Auth patterns", "Diana", true));
        articleRepository.save(new Article("JPA Performance", "Optimization tips", "Diana", true));
    }

    @Test
    @DisplayName("findByTitleContainingIgnoreCase finds articles with 'jpa' in title")
    void searchByTitleKeyword() {
        // TODO: Call articleRepository.findByTitleContainingIgnoreCase("jpa")
        // Assert: 3 articles match (JPA Basics, Advanced JPA, JPA Performance)
        throw new UnsupportedOperationException("Write the test");
    }

    @Test
    @DisplayName("Updating an article persists changes to PostgreSQL")
    void updateArticlePersists() {
        // TODO:
        // 1. Find all articles, get the first one
        // 2. Change its title to "Updated Title"
        // 3. Save it back
        // 4. Read it fresh from the DB using findById
        // 5. Assert the title was updated
        throw new UnsupportedOperationException("Write the test");
    }

    @Test
    @DisplayName("Deleting an article removes it from PostgreSQL")
    void deleteArticleRemoves() {
        // TODO:
        // 1. Count articles (should be 4)
        // 2. Delete the first one
        // 3. Count again (should be 3)
        // 4. Assert findById returns empty for the deleted ID
        throw new UnsupportedOperationException("Write the test");
    }
}
