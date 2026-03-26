# T2-03: Testing REST APIs

**Exercises:** 2 (6 tests total)
**Time:** 45–60 minutes
**Prereqs:** T2-01 (REST Controllers), T1-05 (Testing Foundations)

## What You'll Learn

Advanced testing patterns for REST APIs. After this module, you can write
comprehensive tests for any endpoint — including POST with JSON bodies,
query parameters, response headers, and multi-step test scenarios.

## Exercises

### Exercise 1: Advanced MockMvc ★★☆☆☆

Write MockMvc tests for a complete BookController. Test POST (create),
GET (not found), and DELETE (with setup/teardown).

**Files to edit:** `Ex01_MockMvcAdvancedTest.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_03.Ex01*"
```

### Exercise 2: Query Params & Headers ★★★☆☆

Test search endpoints with query parameters and verify response headers.
Includes multi-step setup (create data, then search).

**Files to edit:** `Ex02_QueryParamsAndHeadersTest.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_03.Ex02*"
```

## Run All T2-03 Tests

```bash
./mvnw test -Dtest="dev/springforge/t2_03/**"
```

## Key Concepts

- **MockMvc.perform(post(...).content(json))** — POST with JSON body
- **MockMvc.perform(get(...).param("key", "value"))** — query parameters
- **andExpect(header().string(...))** — assert response headers
- **andReturn()** — capture response for multi-step tests
- **Test isolation** — each test should set up and clean up its own data
