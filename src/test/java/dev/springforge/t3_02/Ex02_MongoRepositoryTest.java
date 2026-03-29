package dev.springforge.t3_02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: MongoRepository Queries
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Add derived query methods and @Query to ProductRepository.
 *
 * YOUR TASKS:
 * 1. Add findByCategory(String category) — Spring Data derives the query
 * 2. Add findByPriceGreaterThan(BigDecimal price) — derived price filter
 * 3. Add searchByName(@Query) — custom MongoDB query with regex
 * 4. Add findByCategoryOrderByPriceAsc() — sorted results
 * 5. Add countByCategory() — aggregation count
 *
 * WHAT YOU LEARN:
 * - Derived query methods (Spring generates the query from method name)
 * - @Query for custom MongoDB queries
 * - Method naming conventions → query generation rules
 *
 * PREREQ: Complete Ex01 (Product @Document) first. REQUIRES: Docker running.
 */
@DataMongoTest(properties = "spring.autoconfigure.exclude=")
@Import(MongoTestcontainersConfig.class)
@DisplayName("T3-02 Ex02: MongoDB Repository Queries")
class Ex02_MongoRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        // Seed data
        Product p1 = new Product("Laptop", new BigDecimal("999.99"), "Electronics");
        p1.setAvailable(true);
        p1.setTags(List.of("tech", "sale"));

        Product p2 = new Product("Phone", new BigDecimal("599.99"), "Electronics");
        p2.setAvailable(true);
        p2.setTags(List.of("tech", "mobile"));

        Product p3 = new Product("Book", new BigDecimal("29.99"), "Education");
        p3.setAvailable(true);
        p3.setTags(List.of("learning"));

        Product p4 = new Product("Old Phone", new BigDecimal("199.99"), "Electronics");
        p4.setAvailable(false);
        p4.setTags(List.of("tech", "clearance"));

        repository.saveAll(List.of(p1, p2, p3, p4));
    }

    @Test
    @DisplayName("Should find products by category")
    void shouldFindByCategory() {
        List<Product> electronics = repository.findByCategory("Electronics");
        assertThat(electronics).hasSize(3);
    }

    @Test
    @DisplayName("Should find only available products")
    void shouldFindAvailable() {
        List<Product> available = repository.findByAvailableTrue();
        assertThat(available).hasSize(3);
    }

    @Test
    @DisplayName("Should find products under a price")
    void shouldFindByPriceLessThan() {
        List<Product> cheap = repository.findByPriceLessThan(new BigDecimal("300.00"));
        assertThat(cheap).hasSize(2); // Book (29.99) and Old Phone (199.99)
    }

    @Test
    @DisplayName("Should find products by tag")
    void shouldFindByTag() {
        List<Product> techProducts = repository.findByTagsContaining("tech");
        assertThat(techProducts).hasSize(3); // Laptop, Phone, Old Phone
    }

    @Test
    @DisplayName("Should find available products in category under price (@Query)")
    void shouldFindWithCustomQuery() {
        List<Product> result = repository.findAvailableInCategoryUnderPrice(
                "Electronics", new BigDecimal("700.00"));
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getName()).isEqualTo("Phone");
    }
}
