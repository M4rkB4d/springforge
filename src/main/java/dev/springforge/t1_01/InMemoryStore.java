package dev.springforge.t1_01;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 3b: Generic In-Memory Store
 *
 * This mirrors the Spring Data Repository pattern:
 * - save(id, entity)
 * - findById(id) → Optional<T>
 * - findAll() → List<T>
 * - delete(id)
 *
 * YOUR TASK: Implement using a HashMap internally.
 *
 * Hint: Use java.util.HashMap<String, T> as the backing store.
 */
public class InMemoryStore<T> {

    // TODO: Add a private HashMap<String, T> field

    public void save(String id, T entity) {
        // TODO: Put entity into the map
        throw new UnsupportedOperationException("Implement save()");
    }

    public Optional<T> findById(String id) {
        // TODO: Return Optional.ofNullable(map.get(id))
        throw new UnsupportedOperationException("Implement findById()");
    }

    public List<T> findAll() {
        // TODO: Return all values as a List
        throw new UnsupportedOperationException("Implement findAll()");
    }

    public void delete(String id) {
        // TODO: Remove from map
        throw new UnsupportedOperationException("Implement delete()");
    }
}
