# T1-05: Testing Foundations

**Prereqs:** T1-03 (IoC and Dependency Injection)
**Exercises:** 3 (11 tests total)
**Time:** 45–60 minutes

## What You'll Learn

Spring Boot's testing pyramid — from pure unit tests to HTTP-level tests.
After this module, you'll know WHEN to use each test approach and HOW
to write them.

## Exercises

### Exercise 1: Unit Testing ★☆☆☆☆ (Worked Example)

Pure JUnit 5 + AssertJ. No Spring. Study the patterns — you'll use them
in every exercise from here on.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_05.Ex01*"
```

### Exercise 2: @SpringBootTest ★★☆☆☆

Full application context. Test real beans with real injection. Write
the assertions for a pre-built UserService.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_05.Ex02*"
```

### Exercise 3: @WebMvcTest + MockMvc ★★★☆☆

Test the HTTP layer without a full server. Mock dependencies, assert
JSON responses, verify status codes. This is how you test controllers.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_05.Ex03*"
```

## Run All T1-05 Tests

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_05.*"
```

## Key Concepts

| Test Type | Annotation | Speed | Scope |
|---|---|---|---|
| Unit test | None (plain JUnit) | ⚡ Fast | Single class |
| Integration test | @SpringBootTest | 🐢 Slow | Full app context |
| Web slice test | @WebMvcTest | 🚀 Medium | Web layer only |
| Data slice test | @DataJpaTest | 🚀 Medium | JPA layer only |

## Testing Pyramid

```
        /  E2E  \        ← Few, slow, high confidence
       / Web Slice \     ← @WebMvcTest, @DataJpaTest
      / Integration  \   ← @SpringBootTest
     /   Unit Tests    \  ← JUnit 5 + AssertJ (most tests here)
```
