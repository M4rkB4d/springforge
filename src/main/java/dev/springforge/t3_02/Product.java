package dev.springforge.t3_02;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 1: MongoDB Document
 *
 * YOUR TASKS:
 * 1. Add @Document(collection = "products")
 * 2. Add @Field("product_name") to name (custom field name in BSON)
 * 3. Add @Field to tags (stored as "product_tags")
 *
 * KEY DIFFERENCES FROM JPA:
 * - @Document instead of @Entity
 * - @Id from org.springframework.data.annotation (not jakarta.persistence)
 * - @Field instead of @Column (optional — maps Java field to BSON field)
 * - No @Table — uses collection name
 * - No @GeneratedValue — MongoDB generates _id automatically for String IDs
 * - Embedded objects are natural (no @OneToMany needed)
 */
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @Field("product_name")
    private String name;

    private String description;

    private BigDecimal price;

    private String category;

    @Field("product_tags")
    private List<String> tags = new ArrayList<>();

    // Embedded object — no @OneToMany or join table needed!
    private List<Review> reviews = new ArrayList<>();

    private boolean available = true;

    private Instant createdAt = Instant.now();

    public Product() {}

    public Product(String name, BigDecimal price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
