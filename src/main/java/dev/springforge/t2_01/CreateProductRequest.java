package dev.springforge.t2_01;

/**
 * Request DTO for creating a product.
 * Separating the request from the domain model is a best practice —
 * the client shouldn't control the ID.
 */
public record CreateProductRequest(
    String name,
    String description,
    double price
) {}
