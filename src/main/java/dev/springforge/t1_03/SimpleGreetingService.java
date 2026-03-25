package dev.springforge.t1_03;

/**
 * Exercise 1: Simple Greeting Implementation
 *
 * YOUR TASK: Annotate this class with @Service so Spring discovers it.
 * Implement greet() to return "Hello, {name}!"
 *
 * Hint: Add @Service above the class declaration.
 */
// TODO: Add @Service annotation
public class SimpleGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        // TODO: Return "Hello, {name}!"
        throw new UnsupportedOperationException("Implement greet()");
    }
}
