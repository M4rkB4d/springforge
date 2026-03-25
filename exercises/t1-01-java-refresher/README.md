# T1-01: Java for Spring (Refresher)

**Prereqs:** Basic Java knowledge
**Exercises:** 3 (9 tests total)
**Time:** 30–45 minutes

## What You'll Learn

The Java features that Spring Boot uses constantly. If you're rusty on these,
Spring code will look like magic. After this module, it'll make sense.

## Exercises

### Exercise 1: Lambdas & Functional Interfaces ★☆☆☆☆ → ★★☆☆☆

Spring uses `Predicate`, `Function`, and `Supplier` everywhere — for bean
definitions, request routing, and conditional logic. You'll implement each one.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_01.Ex01*"
```

### Exercise 2: Streams & Collectors ★★☆☆☆ → ★★★☆☆

Every service layer processes collections. Filter, transform, group, and
find — the four operations you'll use daily.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_01.Ex02*"
```

### Exercise 3: Generics & Records ★★☆☆☆ → ★★★☆☆

Records are Java's built-in DTOs — Spring Boot 4.0 uses them for response
wrappers, config binding, and event payloads. Generics power Spring Data
repositories. You'll build both.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_01.Ex03*"
```

## Run All T1-01 Tests

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_01.*"
```

## Key Concepts

| Java Feature | Spring Boot Usage |
|---|---|
| Lambdas | Bean definitions, route matchers, callbacks |
| Streams | Service layer data processing |
| Records | DTOs, config properties, event payloads |
| Generics | Repository<T, ID>, ResponseEntity<T> |
| Optional | Repository.findById(), nullable configs |
