package dev.springforge.t1_01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 1: Lambdas & Functional Interfaces
 * ★☆☆☆☆ Worked Example → ★★☆☆☆ Faded Example
 *
 * Spring Boot uses functional interfaces everywhere:
 * - Bean definitions with Supplier
 * - Request mapping with Function
 * - Filtering with Predicate
 *
 * YOUR TASK: Implement the methods in {@link LambdaExercises}.
 */
@DisplayName("T1-01 Ex01: Lambdas & Functional Interfaces")
class Ex01_LambdasAndFunctionalInterfacesTest {

    // ── Worked Example: Predicate ──────────────────────────────────────

    @Test
    @DisplayName("isPositive should return true for positive numbers")
    void isPositive_returnsTrue_forPositiveNumbers() {
        Predicate<Integer> predicate = LambdaExercises.isPositive();
        assertThat(predicate.test(5)).as("5 should be positive").isTrue();
        assertThat(predicate.test(1)).as("1 should be positive").isTrue();
        assertThat(predicate.test(100)).as("100 should be positive").isTrue();
    }

    @Test
    @DisplayName("isPositive should return false for zero and negative numbers")
    void isPositive_returnsFalse_forZeroAndNegative() {
        Predicate<Integer> predicate = LambdaExercises.isPositive();
        assertThat(predicate.test(0)).as("0 should not be positive").isFalse();
        assertThat(predicate.test(-1)).as("-1 should not be positive").isFalse();
        assertThat(predicate.test(-99)).as("-99 should not be positive").isFalse();
    }

    // ── Faded Example: Function ────────────────────────────────────────

    @Test
    @DisplayName("toUpperCase should transform strings to uppercase")
    void toUpperCase_transformsStrings() {
        Function<String, String> fn = LambdaExercises.toUpperCase();
        assertThat(fn.apply("hello")).as("'hello' uppercased").isEqualTo("HELLO");
        assertThat(fn.apply("Spring Boot")).as("'Spring Boot' uppercased").isEqualTo("SPRING BOOT");
    }

    // ── Your Turn: Supplier ────────────────────────────────────────────

    @Test
    @DisplayName("greetingSupplier should return 'Hello, SpringForge!'")
    void greetingSupplier_returnsGreeting() {
        Supplier<String> supplier = LambdaExercises.greetingSupplier();
        assertThat(supplier.get()).as("supplier should return greeting").isEqualTo("Hello, SpringForge!");
    }

    // ── Your Turn: Chaining ────────────────────────────────────────────

    @Test
    @DisplayName("filterAndTransform should filter positive numbers and double them")
    void filterAndTransform_filtersAndDoubles() {
        List<Integer> input = List.of(-3, -1, 0, 2, 5, 8);
        List<Integer> result = LambdaExercises.filterAndTransform(input);
        assertThat(result).as("filter positive then double").containsExactly(4, 10, 16);
    }
}
