package dev.springforge.t1_03;

import org.springframework.stereotype.Service;

/**
 * Exercise 2: Constructor Injection — SOLUTION
 */
@Service
public class NotificationService {

    private final GreetingService greetingService;

    public NotificationService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sendWelcome(String name) {
        return "NOTIFICATION: " + greetingService.greet(name);
    }
}
