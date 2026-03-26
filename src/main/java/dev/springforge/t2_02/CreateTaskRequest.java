package dev.springforge.t2_02;

/**
 * Exercise 1: Add Bean Validation Annotations
 *
 * YOUR TASK:
 * 1. Add @NotBlank to title (title cannot be null or empty)
 * 2. Add @Size(max = 500) to description (max 500 characters)
 * 3. Add @Min(1) and @Max(10) to priority (must be between 1 and 10)
 *
 * Hints:
 * - Import from jakarta.validation.constraints.*
 * - @NotBlank means not null AND not empty/whitespace
 * - @Size works on strings and collections
 * - @Min/@Max work on numbers
 */
public record CreateTaskRequest(
    // TODO: Add @NotBlank
    String title,

    // TODO: Add @Size(max = 500)
    String description,

    // TODO: Add @Min(1) @Max(10)
    int priority
) {}
