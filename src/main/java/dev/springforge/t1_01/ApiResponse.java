package dev.springforge.t1_01;

/**
 * Exercise 3a: API Response Record — SOLUTION
 */
public record ApiResponse<T>(int status, String message, T data) {}
