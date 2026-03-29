package dev.springforge.t1_03;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Exercise 1: Simple Greeting Implementation — SOLUTION
 */
@Service
@Profile("default")
public class SimpleGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
