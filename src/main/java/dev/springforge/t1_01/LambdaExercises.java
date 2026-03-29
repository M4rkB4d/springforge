package dev.springforge.t1_01;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Exercise 1: Lambdas & Functional Interfaces — SOLUTIONS
 */
public class LambdaExercises {

    public static Predicate<Integer> isPositive() {
        return n -> n > 0;
    }

    public static Function<String, String> toUpperCase() {
        return String::toUpperCase;
    }

    public static Supplier<String> greetingSupplier() {
        return () -> "Hello, SpringForge!";
    }

    public static List<Integer> filterAndTransform(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n > 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());
    }
}
