package dev.springforge.t1_03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 3: Spring Profiles
 * ★★★☆☆ Practice
 *
 * Profiles let you swap implementations based on environment:
 * - "formal" profile → FormalGreetingService
 * - default → SimpleGreetingService
 *
 * YOUR TASK: Add @Service and @Profile("formal") to FormalGreetingService,
 * and add @Profile("default") to SimpleGreetingService so they don't conflict.
 */
@SpringBootTest(classes = DiTestApp.class)
@ActiveProfiles("formal")
@DisplayName("T1-03 Ex03: Profiles")
class Ex03_ProfilesTest {

    @Autowired
    private GreetingService greetingService;

    @Test
    @DisplayName("With 'formal' profile, FormalGreetingService should be injected")
    void formalProfileUsesCorrectImpl() {
        assertThat(greetingService).isInstanceOf(FormalGreetingService.class);
    }

    @Test
    @DisplayName("Formal greeting should use polite format")
    void formalGreetingIsPolite() {
        String result = greetingService.greet("Mark");
        assertThat(result).isEqualTo("Good day, Mark. How may I assist you?");
    }

    @Test
    @DisplayName("Formal greeting works with different names")
    void formalGreetingWithDifferentNames() {
        assertThat(greetingService.greet("Sir"))
                .isEqualTo("Good day, Sir. How may I assist you?");
    }
}
