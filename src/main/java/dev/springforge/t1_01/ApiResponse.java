package dev.springforge.t1_01;

/**
 * Exercise 3a: API Response Record
 *
 * Records are Java's built-in DTOs — immutable, with auto-generated
 * equals, hashCode, toString, and accessor methods.
 *
 * Spring Boot 4.0 uses records for:
 * - REST API response wrappers
 * - Configuration property binding
 * - Event payloads
 *
 * YOUR TASK: Define this record with three components:
 * - int status
 * - String message
 * - T data (generic type parameter)
 *
 * Hint: record ApiResponse<T>(int status, String message, T data) {}
 */

// TODO: Replace this class with a record definition
// public record ApiResponse<T>(int status, String message, T data) {}

public class ApiResponse<T> {
    // DELETE this class and replace with the record above
    public ApiResponse(int status, String message, T data) {
        throw new UnsupportedOperationException("Replace this class with a record");
    }

    public int status() { throw new UnsupportedOperationException(); }
    public String message() { throw new UnsupportedOperationException(); }
    public T data() { throw new UnsupportedOperationException(); }
}
