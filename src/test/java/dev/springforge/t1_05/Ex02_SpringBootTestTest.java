package dev.springforge.t1_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * EXERCISE 02: Testing with @SpringBootTest
 *
 * DIFFICULTY: ★★☆☆☆
 *
 * OBJECTIVE: Use @SpringBootTest to load the full application context
 * and test Spring-managed beans with real dependency injection.
 *
 * YOUR TASK: Fill in the assertions for each test method.
 * Look at the UserService implementation to know what to expect.
 *
 * WHAT YOU LEARN:
 * - @SpringBootTest loads the full Spring context
 * - @Autowired injects real beans into your test
 * - assertThat() for value assertions
 * - assertThatThrownBy() for exception assertions
 */
@SpringBootTest(classes = TestingTestApp.class)
@DisplayName("T1-05 Ex02: @SpringBootTest")
class Ex02_SpringBootTestTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("UserService should be injected by Spring")
    void serviceIsInjected() {
        assertThat(userService).as("UserService should be injected").isNotNull();
    }

    @Test
    @DisplayName("formatUsername should lowercase and replace spaces with underscores")
    void formatUsernameTransformsCorrectly() {
        assertThat(userService.formatUsername("John Doe")).as("'John Doe' formatted").isEqualTo("john_doe");
    }

    @Test
    @DisplayName("formatUsername should trim whitespace")
    void formatUsernameTrims() {
        assertThat(userService.formatUsername("  alice  ")).as("whitespace should be trimmed").isEqualTo("alice");
    }

    @Test
    @DisplayName("formatUsername should throw on blank input")
    void formatUsernameThrowsOnBlank() {
        assertThatThrownBy(() -> userService.formatUsername(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("isValidEmail should validate email format")
    void isValidEmailValidates() {
        assertThat(userService.isValidEmail("user@example.com")).as("valid email format").isTrue();
        assertThat(userService.isValidEmail("not-an-email")).as("invalid email format").isFalse();
    }
}
