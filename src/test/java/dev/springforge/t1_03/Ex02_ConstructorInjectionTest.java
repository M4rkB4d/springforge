package dev.springforge.t1_03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 2: Constructor Injection
 * ★★☆☆☆ Faded Example
 *
 * Constructor injection is THE way to do DI in Spring Boot:
 * - Dependencies are clearly declared
 * - Fields can be final (immutable)
 * - No reflection magic needed
 * - Easier to test (just pass mocks to constructor)
 *
 * YOUR TASK: Make NotificationService a @Service with constructor injection.
 */
@SpringBootTest
@DisplayName("T1-03 Ex02: Constructor Injection")
class Ex02_ConstructorInjectionTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    @DisplayName("NotificationService should be auto-injected by Spring")
    void notificationServiceIsInjected() {
        assertThat(notificationService).isNotNull();
    }

    @Test
    @DisplayName("sendWelcome should use injected GreetingService")
    void sendWelcomeUsesInjectedService() {
        String result = notificationService.sendWelcome("Mark");
        assertThat(result).isEqualTo("NOTIFICATION: Hello, Mark!");
    }

    @Test
    @DisplayName("sendWelcome should work with different names")
    void sendWelcomeWorksWithDifferentNames() {
        assertThat(notificationService.sendWelcome("Dev"))
                .isEqualTo("NOTIFICATION: Hello, Dev!");
    }
}
