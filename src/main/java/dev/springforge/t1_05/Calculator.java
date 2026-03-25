package dev.springforge.t1_05;

/**
 * Exercise 1: Unit Testing Target
 *
 * A simple class to test with JUnit 5 and AssertJ.
 * This is already implemented — your job is to write the TESTS.
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }
}
