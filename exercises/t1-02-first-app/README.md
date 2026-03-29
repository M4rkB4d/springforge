# T1-02: Spring Boot First App

## Objective

Build your first Spring Boot application with a REST endpoint that returns a greeting.

## What You'll Do

1. **Ex01**: Create a `@RestController` that returns "Hello, SpringForge!" at `GET /hello`
2. **Ex02**: Add a parameterized greeting at `GET /hello/{name}` that returns "Hello, {name}!"

## How to Run

```bash
# Run the tests for this module
./mvnw test -Dtest="dev/springforge/t1_02/**"

# Or use the progress script
./scripts/check-progress.sh t1-02
```

## Files to Edit

- `src/main/java/dev/springforge/t1_02/HelloController.java`

## Guide

Read the SpringForge Guide T1-02 on Notion for the full concept explanation and worked example.
