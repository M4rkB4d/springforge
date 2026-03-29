package dev.springforge.t2_05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: @DataJpaTest with Testcontainers (Worked Example)
 *
 * DIFFICULTY: ★★☆☆☆
 *
 * This test runs against a REAL PostgreSQL database in Docker.
 * Study the pattern: @DataJpaTest + @Import(TestcontainersConfig.class)
 *
 * REQUIRES: Docker running on your machine.
 *
 * The tests are ALREADY WRITTEN. Study them, run them, understand them.
 */
@DataJpaTest
@Import(TestcontainersConfig.class)
@DisplayName("T2-05 Ex01: @DataJpaTest with Testcontainers")
class Ex01_DataJpaTestWithContainerTest {

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();
        articleRepository.save(new Article("Spring Boot 4.0", "What's new in Boot 4", "Alice", true));
        articleRepository.save(new Article("Hibernate 7 Guide", "Migration tips", "Alice", false));
        articleRepository.save(new Article("React vs Angular", "Frontend comparison", "Bob", true));
    }

    @Test
    @DisplayName("Repository saves and retrieves articles from PostgreSQL")
    void savesAndRetrieves() {
        List<Article> all = articleRepository.findAll();
        assertThat(all).as("should retrieve all 3 articles").hasSize(3);
    }

    @Test
    @DisplayName("findByAuthor filters correctly against real database")
    void findByAuthorFilters() {
        List<Article> aliceArticles = articleRepository.findByAuthor("Alice");
        assertThat(aliceArticles).as("Alice should have 2 articles").hasSize(2);
        assertThat(aliceArticles).extracting(Article::getTitle)
                .as("Alice's article titles")
                .containsExactlyInAnyOrder("Spring Boot 4.0", "Hibernate 7 Guide");
    }

    @Test
    @DisplayName("findByPublishedTrue returns only published articles")
    void findPublishedOnly() {
        List<Article> published = articleRepository.findByPublishedTrue();
        assertThat(published).as("should find 2 published articles").hasSize(2);
        assertThat(published).as("all returned should be published").allMatch(Article::isPublished);
    }
}
