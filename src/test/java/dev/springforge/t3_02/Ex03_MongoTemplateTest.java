package dev.springforge.t3_02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 03: MongoTemplate — When Repository Isn't Enough
 *
 * MongoTemplate gives you full control for:
 * - Criteria queries (like JPA's CriteriaBuilder)
 * - Partial updates ($set, $push, $inc)
 * - Aggregation pipelines ($group, $match, $project)
 *
 * Compare: JPA requires loading the full entity to update one field.
 * MongoDB can update fields in-place without loading the document.
 *
 * REQUIRES: Docker running
 */
@DataMongoTest(properties = "spring.autoconfigure.exclude=")
@Import(MongoTestcontainersConfig.class)
@DisplayName("T3-02 Ex03: MongoTemplate Operations")
class Ex03_MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(Product.class);

        Product p1 = new Product("Laptop", new BigDecimal("999.99"), "Electronics");
        p1.setAvailable(true);
        p1.addReview(new Review("Alice", 5, "Great!"));
        p1.addReview(new Review("Bob", 4, "Good"));

        Product p2 = new Product("Phone", new BigDecimal("599.99"), "Electronics");
        p2.setAvailable(true);
        p2.addReview(new Review("Carol", 3, "Okay"));

        Product p3 = new Product("Book", new BigDecimal("29.99"), "Education");
        p3.setAvailable(true);

        Product p4 = new Product("Tablet", new BigDecimal("449.99"), "Electronics");
        p4.setAvailable(false);

        mongoTemplate.insertAll(List.of(p1, p2, p3, p4));
    }

    @Test
    @DisplayName("Should query with Criteria (like JPA CriteriaBuilder)")
    void shouldQueryWithCriteria() {
        Query query = new Query(
                Criteria.where("category").is("Electronics")
                        .and("price").lte(new BigDecimal("600.00"))
                        .and("available").is(true)
        );

        List<Product> results = mongoTemplate.find(query, Product.class);
        assertThat(results).hasSize(1);
        assertThat(results.getFirst().getName()).isEqualTo("Phone");
    }

    @Test
    @DisplayName("Should update a field without loading the full document")
    void shouldPartialUpdate() {
        // In JPA: load entity -> change field -> save (loads ALL columns)
        // In MongoDB: update just the price, server-side
        Query query = new Query(Criteria.where("name").is("Laptop"));
        Update update = new Update().set("price", new BigDecimal("899.99"));

        mongoTemplate.updateFirst(query, update, Product.class);

        Product updated = mongoTemplate.findOne(query, Product.class);
        assertThat(updated.getPrice()).isEqualByComparingTo(new BigDecimal("899.99"));
    }

    @Test
    @DisplayName("Should push a review into embedded array")
    void shouldPushToEmbeddedArray() {
        Query query = new Query(Criteria.where("name").is("Book"));
        Update update = new Update().push("reviews", new Review("Dave", 5, "Must read!"));

        mongoTemplate.updateFirst(query, update, Product.class);

        Product updated = mongoTemplate.findOne(query, Product.class);
        assertThat(updated.getReviews()).hasSize(1);
        assertThat(updated.getReviews().getFirst().getAuthor()).isEqualTo("Dave");
    }

    @Test
    @DisplayName("Should aggregate: count products per category")
    void shouldAggregateByCategory() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("category").count().as("count"),
                Aggregation.sort(org.springframework.data.domain.Sort.Direction.DESC, "count")
        );

        AggregationResults<Map> results = mongoTemplate.aggregate(agg, "products", Map.class);
        List<Map> mappedResults = results.getMappedResults();

        assertThat(mappedResults).hasSizeGreaterThanOrEqualTo(2);
        // Electronics should have the most (3 products)
        assertThat(mappedResults.getFirst().get("_id")).isEqualTo("Electronics");
        assertThat(((Number) mappedResults.getFirst().get("count")).intValue()).isEqualTo(3);
    }
}
