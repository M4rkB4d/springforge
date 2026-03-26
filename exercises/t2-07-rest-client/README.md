# T2-07: REST Client & Service Layer

**Exercises:** 2 (6 tests total)
**Time:** 45–60 minutes
**Prereqs:** T2-01 (REST Controllers), T2-04 (JPA & Spring Data)

## What You'll Learn

How to call external APIs from your Spring Boot app using the modern
RestClient, and how to structure your code with proper service layers.

## Exercises

### Exercise 1: RestClient ★★★☆☆

Build a REST client using Spring Boot 4.0's RestClient (replaces RestTemplate).
Fluent API, type-safe, no boilerplate.

**Files to edit:** `WeatherClient.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_07.Ex01*"
```

### Exercise 2: Service Layer Pattern ★★★☆☆

Build a service that uses the client and adds business logic.
Tests use Mockito to mock the client — pure unit testing with no HTTP calls.

**Files to edit:** `WeatherService.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_07.Ex02*"
```

## Run All T2-07 Tests

```bash
./mvnw test -Dtest="dev/springforge/t2_07/**"
```

## Key Concepts

- **RestClient** — Spring Boot 4.0's recommended synchronous HTTP client
- **RestClient.Builder** — Spring auto-configures it, inject via constructor
- **Service layer** — business logic between controller and data/client layers
- **@Mock + @InjectMocks** — Mockito patterns for unit testing services
- **Separation of concerns** — client = HOW, service = WHAT, controller = HTTP
