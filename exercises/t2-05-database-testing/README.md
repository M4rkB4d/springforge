# T2-05: Database Testing with Testcontainers

**Exercises:** 2 (6 tests total)
**Time:** 45–60 minutes
**Prereqs:** T2-04 (JPA & Spring Data), T2-03 (Testing REST APIs)

## What You'll Learn

How to test against a REAL database instead of H2. Testcontainers spins up
a PostgreSQL instance in Docker for each test run. Your tests hit a real
database — no more "works in H2 but fails in production."

## Prerequisites

- **Docker must be running** on your machine
- Docker Desktop (Windows/Mac) or Docker Engine (Linux)

## Exercises

### Exercise 1: @DataJpaTest with Testcontainers ★★☆☆☆ (Worked Example)

Study the provided TestcontainersConfig and test class. Run the tests
and verify they pass against real PostgreSQL.

```bash
./mvnw test -Dtest="dev.springforge.t2_05.Ex01*"
```

### Exercise 2: Write Your Own Container Tests ★★★☆☆

The setup is provided. Write test assertions for search, update, and delete
operations against the real database.

**Files to edit:** `Ex02_WriteYourOwnContainerTestTest.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_05.Ex02*"
```

## Run All T2-05 Tests

```bash
./mvnw test -Dtest="dev/springforge/t2_05/**"
```

## Key Concepts

- **Testcontainers** — starts real database containers for tests
- **@ServiceConnection** — auto-configures Spring DataSource from container
- **@TestConfiguration** — reusable test setup shared across test classes
- **@Import** — brings TestcontainersConfig into your test context
- **Docker required** — Testcontainers needs Docker to run containers
