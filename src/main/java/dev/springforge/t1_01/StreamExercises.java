package dev.springforge.t1_01;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Exercise 2: Streams & Collectors — SOLUTIONS
 */
public class StreamExercises {

    public static List<String> namesStartingWith(List<String> names, String prefix) {
        return names.stream()
                .filter(name -> name.toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static String joinUpperCased(List<String> items) {
        return items.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
    }

    public static Map<Character, List<String>> groupByFirstLetter(List<String> names) {
        return names.stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0)));
    }

    public static Optional<String> findLongest(List<String> items) {
        return items.stream()
                .max(Comparator.comparingInt(String::length));
    }
}
