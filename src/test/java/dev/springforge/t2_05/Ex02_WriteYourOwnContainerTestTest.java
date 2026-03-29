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
        List<Article> results = articleRepository.findByTitleContainingIgnoreCase("jpa");
        assertThat(results).as("3 articles contain 'jpa'").hasSize(3);
        assertThat(results).extracting(Article::getTitle)
                .as("titles containing 'jpa'")
                .containsExactlyInAnyOrder("JPA Basics", "Advanced JPA", "JPA Performance");
    }

    @Test
    @DisplayName("Updating an article persists changes to PostgreSQL")
    void updateArticlePersists() {
        Article first = articleRepository.findAll().getFirst();
        first.setTitle("Updated Title");
        articleRepository.save(first);

        Optional<Article> found = articleRepository.findById(first.getId());
        assertThat(found).as("updated article should exist").isPresent();
        assertThat(found.get().getTitle()).as("title should be updated").isEqualTo("Updated Title");
    }

    @Test
    @DisplayName("Deleting an article removes it from PostgreSQL")
    void deleteArticleRemoves() {
        assertThat(articleRepository.count()).as("initial count").isEqualTo(4);

        Article first = articleRepository.findAll().getFirst();
        Long deletedId = first.getId();
        articleRepository.delete(first);

        assertThat(articleRepository.count()).as("count after delete").isEqualTo(3);
        assertThat(articleRepository.findById(deletedId)).as("deleted article should be gone").isEmpty();
    }
}
