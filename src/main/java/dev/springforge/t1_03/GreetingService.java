package dev.springforge.t1_03;

/**
 * Exercise 1: Interface for greeting.
 * Spring's IoC works through interfaces — code to the contract, not the implementation.
 */
public interface GreetingService {
    String greet(String name);
}
