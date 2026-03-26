package dev.springforge.t2_05;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Fully working repository — provided for testing exercises.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByAuthor(String author);

    List<Article> findByPublishedTrue();

    List<Article> findByTitleContainingIgnoreCase(String keyword);
}
