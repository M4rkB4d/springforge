package dev.springforge.t1_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Exercise 3b: Generic In-Memory Store — SOLUTION
 */
public class InMemoryStore<T> {

    private final HashMap<String, T> map = new HashMap<>();

    public void save(String id, T entity) {
        map.put(id, entity);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(map.get(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    public void delete(String id) {
        map.remove(id);
    }
}
