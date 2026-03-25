package dev.springforge.t1_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Exercise 2: Testing with @SpringBootTest
 * ★★☆☆☆ Faded Example
 *
 * @SpringBootTest loads the full application context.
 * Use it when you need Spring's dependency injection in your tests.
 *
 * The test structure is provided. YOUR TASK: Fill in the assertions.
 * Look at the UserService implementation to know what to expect.
 */
@SpringBootTest
@DisplayName("T1-05 Ex02: @SpringBootTest")
class Ex02_SpringBootTestTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("UserService should be injected by Spring")
    void serviceIsInjected() {
        // TODO: Assert that userService is not null
        throw new UnsupportedOperationException("Write the assertion");
    }

    @Test
    @DisplayName("formatUsername should lowercase and replace spaces with underscores")
    void formatUsernameTransformsCorrectly() {
        // TODO: Call userService.formatUsername("John Doe") and assert it equals "john_doe"
        throw new UnsupportedOperationException("Write the assertion");
    }

    @Test
    @DisplayName("formatUsername should trim whitespace")
    void formatUsernameTrims() {
        // TODO: Call userService.formatUsername("  alice  ") and assert it equals "alice"
        throw new UnsupportedOperationException("Write the assertion");
    }

    @Test
    @DisplayName("formatUsername should throw on blank input")
    void formatUsernameThrowsOnBlank() {
        // TODO: Use assertThatThrownBy to verify IllegalArgumentException on blank input
        throw new UnsupportedOperationException("Write the assertion");
    }

    @Test
    @DisplayName("isValidEmail should validate email format")
    void isValidEmailValidates() {
        // TODO: Assert true for "user@example.com", false for "not-an-email"
        throw new UnsupportedOperationException("Write the assertion");
    }
}
