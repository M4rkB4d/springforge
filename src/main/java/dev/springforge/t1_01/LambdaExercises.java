package dev.springforge.t1_01;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Exercise 1: Lambdas & Functional Interfaces
 *
 * Spring Boot relies heavily on Java's functional interfaces.
 * Implement each method using lambda expressions.
 */
public class LambdaExercises {

    /**
     * WORKED EXAMPLE — Returns a Predicate that checks if a number is positive (> 0).
     *
     * A Predicate is a function that takes one argument and returns boolean.
     * Spring uses Predicates for filtering, route matching, and conditional logic.
     */
    public static Predicate<Integer> isPositive() {
        // TODO: Return a lambda that returns true when n > 0
        throw new UnsupportedOperationException("Implement isPositive()");
    }

    /**
     * FADED EXAMPLE — Returns a Function that converts a String to uppercase.
     *
     * A Function takes one argument and returns a result.
     * Hint: Use String::toUpperCase or s -> s.toUpperCase()
     */
    public static Function<String, String> toUpperCase() {
        // TODO: Return a lambda or method reference
        throw new UnsupportedOperationException("Implement toUpperCase()");
    }

    /**
     * YOUR TURN — Returns a Supplier that produces "Hello, SpringForge!".
     *
     * A Supplier takes no arguments and returns a value.
     * Spring uses Suppliers for lazy bean initialization.
     */
    public static Supplier<String> greetingSupplier() {
        // TODO: Return a lambda that supplies the greeting
        throw new UnsupportedOperationException("Implement greetingSupplier()");
    }

    /**
     * YOUR TURN — Filter positive numbers from the list, then double each one.
     *
     * Use Stream API with filter() and map().
     * This pattern appears constantly in Spring service layers.
     */
    public static List<Integer> filterAndTransform(List<Integer> numbers) {
        // TODO: Use streams to filter positive numbers and multiply each by 2
        throw new UnsupportedOperationException("Implement filterAndTransform()");
    }
}
