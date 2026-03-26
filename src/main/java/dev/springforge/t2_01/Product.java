package dev.springforge.t2_01;

/**
 * Domain model for a Product.
 * In a real app this would be a JPA entity — for now it's a plain record.
 * We'll convert it to a JPA entity in T2-04.
 */
public record Product(
    Long id,
    String name,
    String description,
    double price
) {
    public Product withId(Long newId) {
        return new Product(newId, name, description, price);
    }
}
