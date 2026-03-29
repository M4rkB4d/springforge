package dev.springforge.t1_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Exercise 2: Testing with @SpringBootTest — SOLUTION
 */
@SpringBootTest(classes = TestingTestApp.class)
@DisplayName("T1-05 Ex02: @SpringBootTest")
class Ex02_SpringBootTestTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("UserService should be injected by Spring")
    void serviceIsInjected() {
        assertThat(userService).isNotNull();
    }

    @Test
    @DisplayName("formatUsername should lowercase and replace spaces with underscores")
    void formatUsernameTransformsCorrectly() {
        assertThat(userService.formatUsername("John Doe")).isEqualTo("john_doe");
    }

    @Test
    @DisplayName("formatUsername should trim whitespace")
    void formatUsernameTrims() {
        assertThat(userService.formatUsername("  alice  ")).isEqualTo("alice");
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
        assertThat(userService.isValidEmail("user@example.com")).isTrue();
        assertThat(userService.isValidEmail("not-an-email")).isFalse();
    }
}
