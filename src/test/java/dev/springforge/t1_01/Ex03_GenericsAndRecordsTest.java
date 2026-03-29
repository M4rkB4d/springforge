package dev.springforge.t1_01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * EXERCISE 03: Generics & Records
 *
 * DIFFICULTY: ★★★☆☆
 *
 * Faded Example → Practice
 *
 * Spring Boot 4.0 uses records extensively for DTOs and configuration.
 * Generics power the entire Spring Data repository system.
 *
 * YOUR TASK: Implement the classes in this package.
 */
@DisplayName("T1-01 Ex03: Generics & Records")
class Ex03_GenericsAndRecordsTest {

    // ── Records as DTOs ────────────────────────────────────────────────

    @Test
    @DisplayName("ApiResponse record should hold status and data")
    void apiResponse_holdsData() {
        ApiResponse<String> response = new ApiResponse<>(200, "OK", "Hello");
        assertThat(response.status()).isEqualTo(200);
        assertThat(response.message()).isEqualTo("OK");
        assertThat(response.data()).isEqualTo("Hello");
    }

    @Test
    @DisplayName("ApiResponse should support different data types")
    void apiResponse_supportsGenerics() {
        ApiResponse<List<Integer>> response = new ApiResponse<>(200, "OK", List.of(1, 2, 3));
        assertThat(response.data()).containsExactly(1, 2, 3);
    }

    // ── Generic Repository Pattern ─────────────────────────────────────

    @Test
    @DisplayName("InMemoryStore should save and find by id")
    void inMemoryStore_savesAndFinds() {
        InMemoryStore<String> store = new InMemoryStore<>();
        store.save("key1", "value1");
        store.save("key2", "value2");

        assertThat(store.findById("key1")).isPresent().contains("value1");
        assertThat(store.findById("key2")).isPresent().contains("value2");
        assertThat(store.findById("key3")).isEmpty();
    }

    @Test
    @DisplayName("InMemoryStore should list all values")
    void inMemoryStore_listsAll() {
        InMemoryStore<Integer> store = new InMemoryStore<>();
        store.save("a", 10);
        store.save("b", 20);

        assertThat(store.findAll()).containsExactlyInAnyOrder(10, 20);
    }

    @Test
    @DisplayName("InMemoryStore should delete by id")
    void inMemoryStore_deletesById() {
        InMemoryStore<String> store = new InMemoryStore<>();
        store.save("key1", "value1");
        store.delete("key1");

        assertThat(store.findById("key1")).isEmpty();
    }
}
