package dev.springforge.t3_02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: MongoDB Document Basics
 *
 * YOUR TASKS:
 * 1. Add @Document(collection = "products") to Product class
 * 2. Add @Field annotations as described in the Product javadoc
 *
 * This uses @DataMongoTest (slice test) — only MongoDB beans are loaded.
 *
 * REQUIRES: Docker running
 */
@DataMongoTest
@Import(MongoTestcontainersConfig.class)
@DisplayName("T3-02 Ex01: MongoDB Document")
class Ex01_MongoDocumentTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(Product.class);
    }

    @Test
    @DisplayName("Should save and retrieve a product document")
    void shouldSaveAndRetrieve() {
        Product product = new Product("Spring Boot Guide", new BigDecimal("49.99"), "Books");
        product.setDescription("Complete guide to Spring Boot 4.0");

        Product saved = mongoTemplate.save(product);
        assertThat(saved.getId()).isNotNull();

        Product found = mongoTemplate.findById(saved.getId(), Product.class);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Spring Boot Guide");
    }

    @Test
    @DisplayName("Should store embedded reviews inside the product document")
    void shouldEmbedReviews() {
        Product product = new Product("Java Cookbook", new BigDecimal("39.99"), "Books");
        product.addReview(new Review("Alice", 5, "Excellent!"));
        product.addReview(new Review("Bob", 4, "Very good"));

        Product saved = mongoTemplate.save(product);
        Product found = mongoTemplate.findById(saved.getId(), Product.class);

        assertThat(found.getReviews()).hasSize(2);
        assertThat(found.getReviews().getFirst().getAuthor()).isEqualTo("Alice");
    }

    @Test
    @DisplayName("Product should be stored in 'products' collection")
    void shouldUseCorrectCollection() {
        Product product = new Product("Test Item", new BigDecimal("9.99"), "Test");
        mongoTemplate.save(product);

        assertThat(mongoTemplate.collectionExists("products"))
                .as("Product should map to 'products' collection via @Document")
                .isTrue();

        long count = mongoTemplate.getCollection("products").countDocuments();
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("Custom @Field names should be used in BSON")
    void shouldUseCustomFieldNames() {
        Product product = new Product("Widget", new BigDecimal("19.99"), "Hardware");
        product.getTags().add("sale");
        mongoTemplate.save(product);

        // Query using the BSON field name (not Java field name)
        var doc = mongoTemplate.getCollection("products").find().first();
        assertThat(doc).isNotNull();
        assertThat(doc.containsKey("product_name"))
                .as("name field should be stored as 'product_name' via @Field")
                .isTrue();
        assertThat(doc.containsKey("product_tags"))
                .as("tags field should be stored as 'product_tags' via @Field")
                .isTrue();
    }
}
