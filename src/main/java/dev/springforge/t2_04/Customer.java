package dev.springforge.t2_04;

import jakarta.persistence.*;

/**
 * Exercise 1: JPA Entity
 *
 * YOUR TASKS:
 * 1. Add @Table(name = "customers") annotation
 * 2. Add @Column(nullable = false) to name
 * 3. Add @Column(unique = true, nullable = false) to email
 *
 * The @Entity, @Id, and @GeneratedValue are already set up.
 * Your job: add column constraints that enforce data integrity.
 *
 * Note: This is a class, not a record — JPA entities need mutable state
 * and a no-arg constructor.
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    // JPA requires a no-arg constructor
    protected Customer() {}

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
