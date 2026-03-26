package dev.springforge.t3_02;

import java.time.Instant;

/**
 * Embedded document — stored inside the Product document.
 *
 * In MongoDB, this is embedded directly (no separate collection).
 * In JPA, this would require a separate table + @OneToMany.
 *
 * This is the MongoDB "embed for one-to-few" pattern.
 */
public class Review {

    private String author;
    private int rating;
    private String comment;
    private Instant createdAt = Instant.now();

    public Review() {}

    public Review(String author, int rating, String comment) {
        this.author = author;
        this.rating = rating;
        this.comment = comment;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
