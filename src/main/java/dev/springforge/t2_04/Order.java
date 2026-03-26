package dev.springforge.t2_04;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Exercise 3: Entity Relationships
 *
 * YOUR TASKS:
 * 1. Add @Entity and @Table(name = "orders")
 * 2. Add @Id and @GeneratedValue(strategy = GenerationType.IDENTITY) to id
 * 3. Add a @ManyToOne relationship to Customer:
 *    - @ManyToOne(fetch = FetchType.LAZY)
 *    - @JoinColumn(name = "customer_id", nullable = false)
 * 4. The rest of the fields are basic columns
 *
 * This teaches: entity relationships, fetch types, and join columns.
 */
// TODO: Add @Entity
// TODO: Add @Table(name = "orders")
public class Order {

    // TODO: Add @Id and @GeneratedValue
    private Long id;

    // TODO: Add @ManyToOne(fetch = FetchType.LAZY) and @JoinColumn
    private Customer customer;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    private LocalDateTime createdAt;

    protected Order() {}

    public Order(Customer customer, String description, BigDecimal total) {
        this.customer = customer;
        this.description = description;
        this.total = total;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public String getDescription() { return description; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
