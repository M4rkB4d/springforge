package dev.springforge.t1_03;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Exercise 3: Profiles — Formal greeting — SOLUTION
 */
@Service
@Profile("formal")
public class FormalGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Good day, " + name + ". How may I assist you?";
    }
}
