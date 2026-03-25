package dev.springforge.t1_03;

/**
 * Exercise 2: Constructor Injection
 *
 * YOUR TASK:
 * 1. Annotate with @Service
 * 2. Add a constructor that accepts GreetingService
 * 3. Store it in a final field
 * 4. Implement sendWelcome() using the injected service
 *
 * Spring auto-injects the GreetingService bean via constructor injection.
 * No @Autowired needed when there's only one constructor.
 */
// TODO: Add @Service annotation
public class NotificationService {

    // TODO: Add a final GreetingService field

    // TODO: Add constructor with GreetingService parameter

    /**
     * Send a welcome notification.
     * Should return: "NOTIFICATION: {greeting}" where greeting comes from GreetingService.
     */
    public String sendWelcome(String name) {
        // TODO: Use injected GreetingService to build the notification
        throw new UnsupportedOperationException("Implement sendWelcome()");
    }
}
