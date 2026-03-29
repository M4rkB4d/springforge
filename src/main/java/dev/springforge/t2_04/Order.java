package dev.springforge.t2_04;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Exercise 3: Entity Relationships
 *
 * YOUR TASKS:
 * 1. Add @Table(name = "orders")
 * 2. Add @JoinColumn to the customer field:
 *    - @JoinColumn(name = "customer_id", nullable = false)
 *    (@ManyToOne is already provided — your job is the join column mapping)
 *
 * The @Entity, @Id, @GeneratedValue, and @ManyToOne are already set up.
 * Your job: add the table name and join column constraint.
 *
 * This teaches: entity relationships, fetch types, and join columns.
 */
@Entity
// TODO: Add @Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    // TODO: Add @JoinColumn(name = "customer_id", nullable = false)
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
