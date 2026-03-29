package dev.springforge.t1_05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * EXERCISE 01: Unit Testing with JUnit 5 + AssertJ
 *
 * DIFFICULTY: ★☆☆☆☆
 *
 * Worked Example
 *
 * No Spring context here — pure unit testing. Fast, focused, isolated.
 *
 * This exercise is ALREADY COMPLETE as a worked example.
 * Study the patterns: @Test, @DisplayName, @BeforeEach, assertThat, assertThatThrownBy.
 */
@DisplayName("T1-05 Ex01: Unit Testing")
class Ex01_UnitTestingTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("add() should sum two numbers")
    void addSumsTwoNumbers() {
        assertThat(calculator.add(2, 3)).as("add(2, 3) should return 5").isEqualTo(5);
        assertThat(calculator.add(-1, 1)).as("add(-1, 1) should return 0").isEqualTo(0);
        assertThat(calculator.add(0, 0)).as("add(0, 0) should return 0").isEqualTo(0);
    }

    @Test
    @DisplayName("divide() should divide correctly")
    void divideDividesCorrectly() {
        assertThat(calculator.divide(10, 2)).as("divide(10, 2) should return 5").isEqualTo(5);
        assertThat(calculator.divide(7, 2)).as("divide(7, 2) integer division").isEqualTo(3); // integer division
    }

    @Test
    @DisplayName("divide() should throw on division by zero")
    void divideThrowsOnZero() {
        assertThatThrownBy(() -> calculator.divide(10, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("Division by zero");
    }

    @Test
    @DisplayName("isEven() should identify even and odd numbers")
    void isEvenIdentifiesCorrectly() {
        assertThat(calculator.isEven(4)).as("4 should be even").isTrue();
        assertThat(calculator.isEven(7)).as("7 should be odd").isFalse();
        assertThat(calculator.isEven(0)).as("0 should be even").isTrue();
    }
}
