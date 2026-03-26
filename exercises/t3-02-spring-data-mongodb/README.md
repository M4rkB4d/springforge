# T3-02: Spring Data MongoDB

## What You'll Learn
- MongoDB document model vs JPA entity model
- @Document, @Field, embedded objects (no joins needed!)
- MongoRepository for CRUD (same interface as JpaRepository)
- MongoTemplate for advanced operations (partial updates, aggregations)
- @DataMongoTest slice testing with Testcontainers

## Prerequisites
- T2-04: JPA & Spring Data (to understand the contrast)
- Docker running

## Key Concepts

### JPA vs MongoDB — Side by Side
| Concept | JPA | MongoDB |
|---|---|---|
| Class annotation | `@Entity` | `@Document` |
| Field mapping | `@Column` | `@Field` |
| Identity | `@Id` (jakarta.persistence) | `@Id` (spring.data.annotation) |
| Relationships | `@OneToMany`, join tables | Embed directly in document |
| Repository | `JpaRepository<T, ID>` | `MongoRepository<T, ID>` |
| Custom queries | JPQL / CriteriaBuilder | JSON @Query / Criteria API |
| Partial updates | Load entity → change → save | `Update.set()` (server-side) |

### When to Embed vs Reference
- **Embed**: data always read together, one-to-few, small documents
- **Reference**: data read independently, one-to-many, unbounded growth

## Exercises

### Ex01: MongoDB Document Basics
Add `@Document` and `@Field` annotations to Product. Tests verify the document structure.

### Ex02: MongoRepository Queries
Run tests that exercise derived queries and `@Query` with JSON filters.

### Ex03: MongoTemplate Operations
See how Criteria queries, partial updates, and aggregation pipelines work.

## Running Tests
```bash
./mvnw test -Dtest="dev/springforge/t3_02/**"
```
