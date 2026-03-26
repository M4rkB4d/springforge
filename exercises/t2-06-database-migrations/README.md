# T2-06: Database Migrations with Flyway

**Exercises:** 2 (6 tests total)
**Time:** 30–45 minutes
**Prereqs:** T2-04 (JPA & Spring Data)

## What You'll Learn

How to evolve your database schema safely over time using Flyway.
Every schema change becomes a versioned migration file — no more
"just ALTER the production database and hope for the best."

## Exercises

### Exercise 1: Flyway Migrations ★★☆☆☆ (Worked Example)

Study the existing V1 and V2 migrations. Run the tests to verify
Flyway ran them automatically against the Testcontainers PostgreSQL.

```bash
./mvnw test -Dtest="dev.springforge.t2_06.Ex01*"
```

### Exercise 2: Write Your Own Migration ★★★☆☆

Create V3__add_tags_table.sql that adds a tags table and
an article_tags join table. The tests verify your SQL is correct.

**Files to create:** `src/main/resources/db/migration/V3__add_tags_table.sql`

```bash
./mvnw test -Dtest="dev.springforge.t2_06.Ex02*"
```

## Run All T2-06 Tests

```bash
./mvnw test -Dtest="dev/springforge/t2_06/**"
```

## Key Concepts

- **Flyway** — version-controlled database migrations
- **V{n}__{description}.sql** — naming convention (two underscores!)
- **Automatic execution** — Spring Boot runs pending migrations on startup
- **Immutable migrations** — never edit a migration after it's been applied
- **Forward-only** — add V3 to change V1's schema, don't modify V1
