package dev.springforge.t1_01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 2: Streams & Collectors
 * ★★☆☆☆ Faded Example → ★★★☆☆ Practice
 *
 * Spring service layers transform data constantly.
 * Master streams = master Spring data processing.
 *
 * YOUR TASK: Implement the methods in {@link StreamExercises}.
 */
@DisplayName("T1-01 Ex02: Streams & Collectors")
class Ex02_StreamsAndCollectorsTest {

    private final List<String> names = List.of("Alice", "Bob", "Charlie", "Anna", "Alex");

    // ── Filter + Collect ───────────────────────────────────────────────

    @Test
    @DisplayName("namesStartingWith should filter names by prefix (case-insensitive)")
    void namesStartingWith_filtersCorrectly() {
        List<String> result = StreamExercises.namesStartingWith(names, "A");
        assertThat(result).containsExactly("Alice", "Anna", "Alex");
    }

    @Test
    @DisplayName("namesStartingWith should return empty for no matches")
    void namesStartingWith_emptyForNoMatch() {
        List<String> result = StreamExercises.namesStartingWith(names, "Z");
        assertThat(result).isEmpty();
    }

    // ── Map + Join ─────────────────────────────────────────────────────

    @Test
    @DisplayName("joinUpperCased should uppercase and join with comma")
    void joinUpperCased_transformsAndJoins() {
        String result = StreamExercises.joinUpperCased(List.of("spring", "boot", "rocks"));
        assertThat(result).isEqualTo("SPRING, BOOT, ROCKS");
    }

    // ── GroupBy ────────────────────────────────────────────────────────

    @Test
    @DisplayName("groupByFirstLetter should group names by their first character")
    void groupByFirstLetter_groupsCorrectly() {
        Map<Character, List<String>> result = StreamExercises.groupByFirstLetter(names);
        assertThat(result.get('A')).containsExactly("Alice", "Anna", "Alex");
        assertThat(result.get('B')).containsExactly("Bob");
        assertThat(result.get('C')).containsExactly("Charlie");
    }

    // ── Find + Optional ────────────────────────────────────────────────

    @Test
    @DisplayName("findLongest should return the longest string")
    void findLongest_returnsLongest() {
        Optional<String> result = StreamExercises.findLongest(names);
        assertThat(result).isPresent().contains("Charlie");
    }

    @Test
    @DisplayName("findLongest should return empty for empty list")
    void findLongest_emptyForEmptyList() {
        Optional<String> result = StreamExercises.findLongest(List.of());
        assertThat(result).isEmpty();
    }
}
