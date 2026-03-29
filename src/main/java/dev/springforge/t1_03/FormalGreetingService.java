package dev.springforge.t1_03;

/**
 * Exercise 3: Profiles — Formal greeting for "production" profile.
 *
 * YOUR TASK:
 * 1. Annotate with @Service
 * 2. Annotate with @Profile("formal") so it only activates for that profile
 * 3. Implement greet() to return "Good day, {name}. How may I assist you?"
 *
 * When the "formal" profile is active, THIS bean replaces SimpleGreetingService.
 * (SimpleGreetingService needs @Profile("default") to avoid both beans being active.)
 */
// TODO: Add @Service and @Profile("formal") annotations
public class FormalGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        // TODO: Return formal greeting
        throw new UnsupportedOperationException("Implement greet()");
    }
}
