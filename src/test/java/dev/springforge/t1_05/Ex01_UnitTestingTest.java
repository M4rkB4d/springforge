package dev.springforge.t1_05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Exercise 1: Unit Testing with JUnit 5 + AssertJ
 * ★☆☆☆☆ Worked Example
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
        assertThat(calculator.add(2, 3)).isEqualTo(5);
        assertThat(calculator.add(-1, 1)).isEqualTo(0);
        assertThat(calculator.add(0, 0)).isEqualTo(0);
    }

    @Test
    @DisplayName("divide() should divide correctly")
    void divideDividesCorrectly() {
        assertThat(calculator.divide(10, 2)).isEqualTo(5);
        assertThat(calculator.divide(7, 2)).isEqualTo(3); // integer division
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
        assertThat(calculator.isEven(4)).isTrue();
        assertThat(calculator.isEven(7)).isFalse();
        assertThat(calculator.isEven(0)).isTrue();
    }
}
