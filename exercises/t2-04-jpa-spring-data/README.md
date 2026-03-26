# T2-04: JPA & Spring Data

**Exercises:** 3 (9 tests total)
**Time:** 60–90 minutes
**Prereqs:** T2-01 (REST Controllers)

## What You'll Learn

How to persist data to a database using JPA and Spring Data. After this module,
you can define entities, create repositories, write queries, and model
relationships between tables.

## Exercises

### Exercise 1: JPA Entity ★★☆☆☆

Annotate the Customer class as a JPA entity with proper column mappings.

**Files to edit:** `Customer.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_04.Ex01*"
```

### Exercise 2: Spring Data Repository ★★★☆☆

Create a Spring Data repository with derived query methods. Spring generates
the SQL from the method name automatically.

**Files to edit:** `CustomerRepository.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_04.Ex02*"
```

### Exercise 3: Relationships & Custom Queries ★★★☆☆

Add a @ManyToOne relationship between Order and Customer. Create a repository
with both derived queries and a custom @Query method.

**Files to edit:** `Order.java`, `OrderRepository.java`

```bash
./mvnw test -Dtest="dev.springforge.t2_04.Ex03*"
```

## Run All T2-04 Tests

```bash
./mvnw test -Dtest="dev/springforge/t2_04/**"
```

## Key Concepts

- **@Entity** — marks a class as a JPA entity (mapped to a database table)
- **@Id, @GeneratedValue** — primary key with auto-generation
- **@Column** — customize column constraints (nullable, unique, length)
- **JpaRepository<T, ID>** — Spring Data interface with CRUD + paging + sorting
- **Derived queries** — method names parsed into SQL (findByEmail, findByNameContaining)
- **@Query** — custom JPQL when derived queries aren't enough
- **@ManyToOne** — many entities reference one parent entity
- **@JoinColumn** — specifies the foreign key column
