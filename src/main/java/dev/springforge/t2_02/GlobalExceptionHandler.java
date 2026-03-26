package dev.springforge.t2_02;

/**
 * Exercise 2: Global Exception Handler
 *
 * A @RestControllerAdvice handles exceptions across ALL controllers.
 * Much cleaner than try/catch in every method.
 *
 * YOUR TASKS:
 * 1. Annotate this class with @RestControllerAdvice
 * 2. Create a method that handles TaskNotFoundException:
 *    - Annotate with @ExceptionHandler(TaskNotFoundException.class)
 *    - Return ResponseEntity with 404 status
 *    - Body should be a ProblemDetail (RFC 9457)
 *
 * Hint: Use ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage())
 *       Then set the title: problemDetail.setTitle("Task Not Found")
 *
 * 3. Create a method that handles MethodArgumentNotValidException:
 *    - This is thrown automatically when @Valid fails
 *    - Return 400 with a ProblemDetail containing the field errors
 *
 * Hint: Use ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed")
 *       Add field errors via problemDetail.setProperty("errors", fieldErrors)
 */
// TODO: Add @RestControllerAdvice
public class GlobalExceptionHandler {

    // TODO: Add @ExceptionHandler(TaskNotFoundException.class)
    // TODO: Return ResponseEntity<ProblemDetail> for 404

    // TODO: Add @ExceptionHandler(MethodArgumentNotValidException.class)
    // TODO: Return ResponseEntity<ProblemDetail> for 400 with field errors
}
