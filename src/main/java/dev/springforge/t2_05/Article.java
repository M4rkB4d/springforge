package dev.springforge.t2_05;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * A fully annotated JPA entity for testing exercises.
 * Already implemented — your job is to write the TESTS.
 */
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(name = "published")
    private boolean published;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Article() {}

    public Article(String title, String content, String author, boolean published) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.published = published;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public boolean isPublished() { return published; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setTitle(String title) { this.title = title; }
    public void setPublished(boolean published) { this.published = published; }
}
