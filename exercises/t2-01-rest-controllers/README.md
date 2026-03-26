# T2-01: REST Controllers — Full CRUD

**Exercises:** 3 (10 tests total)
**Time:** 45–60 minutes
**Prereqs:** T1-04 (Auto-Configuration), T1-05 (Testing)

## What You'll Learn

Building a complete REST API with all HTTP methods. After this module,
you can create, read, update, and delete resources via HTTP — the foundation
of every backend service.

## Exercises

### Exercise 1: GET Endpoints ★★☆☆☆

List all products and retrieve one by ID. Return proper status codes —
200 for success, 404 when not found.

**Files to edit:** `ProductController.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_01.Ex01*"
```

### Exercise 2: POST Endpoint ★★★☆☆

Accept a JSON request body and create a new product. Return 201 Created
with the saved product (including its generated ID).

**Files to edit:** `ProductController.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_01.Ex02*"
```

### Exercise 3: PUT & DELETE Endpoints ★★★☆☆

Update an existing product (PUT) and delete one (DELETE). Return appropriate
status codes: 200 for update, 204 for delete, 404 if the product doesn't exist.

**Files to edit:** `ProductController.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_01.Ex03*"
```

## Run All T2-01 Tests

```bash
./mvnw test -Dtest="dev/springforge/t2_01/**"
```

## Key Concepts

- **@RequestMapping** — base path for all endpoints in a controller
- **@PostMapping** — maps POST requests
- **@PutMapping** — maps PUT requests
- **@DeleteMapping** — maps DELETE requests
- **@RequestBody** — deserializes JSON request body to a Java object
- **@PathVariable** — extracts values from URL path segments
- **ResponseEntity** — control status code, headers, and body of the response
