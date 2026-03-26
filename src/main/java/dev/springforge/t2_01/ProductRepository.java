package dev.springforge.t2_01;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory repository for Products.
 * Simulates a database — replaced by Spring Data JPA in T2-04.
 *
 * This is provided for you. You don't need to modify this class.
 */
@Repository
public class ProductRepository {

    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public Product save(Product product) {
        Long id = product.id() != null ? product.id() : sequence.getAndIncrement();
        Product saved = product.withId(id);
        store.put(id, saved);
        return saved;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(Long id) {
        store.remove(id);
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}
