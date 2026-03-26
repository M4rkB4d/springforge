# T2-02: Validation & Error Handling

**Exercises:** 3 (8 tests total)
**Time:** 45–60 minutes
**Prereqs:** T2-01 (REST Controllers)

## What You'll Learn

How to validate input and return proper error responses. After this module,
your APIs reject bad data with clear, structured error messages — not stack traces.

## Exercises

### Exercise 1: Bean Validation ★★☆☆☆

Add validation annotations to CreateTaskRequest and trigger them with @Valid.

**Files to edit:** `CreateTaskRequest.java`, `TaskController.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_02.Ex01*"
```

### Exercise 2: Global Exception Handler ★★★☆☆

Create a @RestControllerAdvice that catches TaskNotFoundException and returns
a ProblemDetail (RFC 9457) response with proper 404 status.

**Files to edit:** `GlobalExceptionHandler.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_02.Ex02*"
```

### Exercise 3: ProblemDetail with Field Errors ★★★☆☆

Handle MethodArgumentNotValidException in your exception handler.
Return a ProblemDetail that includes field-level error messages.

**Files to edit:** `GlobalExceptionHandler.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_02.Ex03*"
```

## Run All T2-02 Tests

```bash
./mvnw test -Dtest="dev/springforge/t2_02/**"
```

## Key Concepts

- **@Valid** — triggers validation on request bodies
- **@NotBlank, @Size, @Min, @Max** — Bean Validation constraint annotations
- **@RestControllerAdvice** — global exception handler across all controllers
- **@ExceptionHandler** — maps exceptions to handler methods
- **ProblemDetail** — RFC 9457 standard error response format
