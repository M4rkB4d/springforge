package dev.springforge.t1_01;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Exercise 2: Streams & Collectors
 *
 * Spring service layers process collections constantly.
 * These patterns will appear in every controller and service you write.
 */
public class StreamExercises {

    /**
     * Filter names that start with the given prefix (case-insensitive).
     * Return them in their original order.
     *
     * Hint: String.startsWith() is case-sensitive, so normalize first.
     */
    public static List<String> namesStartingWith(List<String> names, String prefix) {
        // TODO: Use stream().filter().collect()
        throw new UnsupportedOperationException("Implement namesStartingWith()");
    }

    /**
     * Convert each string to uppercase, then join with ", ".
     *
     * Example: ["spring", "boot"] → "SPRING, BOOT"
     *
     * Hint: Use Collectors.joining()
     */
    public static String joinUpperCased(List<String> items) {
        // TODO: Use stream().map().collect(Collectors.joining(...))
        throw new UnsupportedOperationException("Implement joinUpperCased()");
    }

    /**
     * Group names by their first character.
     *
     * Example: ["Alice", "Anna", "Bob"] → {'A': ["Alice", "Anna"], 'B': ["Bob"]}
     *
     * Hint: Use Collectors.groupingBy()
     */
    public static Map<Character, List<String>> groupByFirstLetter(List<String> names) {
        // TODO: Use stream().collect(Collectors.groupingBy(...))
        throw new UnsupportedOperationException("Implement groupByFirstLetter()");
    }

    /**
     * Find the longest string in the list. Return Optional.empty() if list is empty.
     *
     * Hint: Use stream().max() with Comparator.comparingInt()
     */
    public static Optional<String> findLongest(List<String> items) {
        // TODO: Use stream().max(...)
        throw new UnsupportedOperationException("Implement findLongest()");
    }
}
