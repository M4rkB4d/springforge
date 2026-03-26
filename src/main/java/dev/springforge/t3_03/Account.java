package dev.springforge.t3_03;

import jakarta.persistence.*;

/**
 * Exercise 1: JPA Entity — Account (stored in SQL database)
 *
 * This entity is already set up. It lives in the relational database
 * alongside the MongoDB documents. Notice how JPA annotations (@Entity)
 * are completely separate from MongoDB annotations (@Document).
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    protected Account() {}

    public Account(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
