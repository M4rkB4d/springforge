# T3-03: Dual Data Stores (JPA + MongoDB)

## What You'll Learn
- Running JPA (SQL Server) and Spring Data MongoDB side by side
- Configuring `@EnableJpaRepositories` and `@EnableMongoRepositories`
- Cross-store queries (SQL lookup → MongoDB query)
- Mongock migrations for versioned MongoDB changes

## Prerequisites
- T3-01 (Azure SQL + Flyway)
- T3-02 (Spring Data MongoDB)
- Docker running (SQL Server + MongoDB containers)

## Why Dual Data Stores?

Real applications often use multiple databases. A common pattern:
- **SQL** for structured, relational data (accounts, transactions, orders)
- **MongoDB** for flexible, high-volume data (audit logs, events, analytics)

Spring Boot handles this naturally — JPA and MongoDB starters coexist.
The key is explicit configuration so Spring knows which repository
talks to which database.

## Exercises

### Exercise 1: Dual Store Setup (Worked Example)
See `Ex01_DualStoreSetupTest.java` — demonstrates both stores active.

### Exercise 2: Repositories and Cross-Store Queries
**Files to edit:**
- `AuditLog.java` — Add `@Document` and `@Field` annotations
- `DualStoreConfig.java` — Add `@EnableJpaRepositories` and `@EnableMongoRepositories`
- `AuditLogRepository.java` — Add `findByAccountId` and `findByAction` methods

```bash
./mvnw test -Dtest="dev.springforge.t3_03.Ex02_DualStoreRepositoriesTest"
```

### Exercise 3: Mongock Migration
> ⚠️ **Currently disabled.** Mongock 5.5.0 (`mongock-springboot-v3`) is incompatible with Spring Boot 4.0 / Spring Data 5.x. The test is `@Disabled` and changeset annotations are commented out. The exercise code and concepts are preserved — re-enable when Mongock ships a Boot 4.0 artifact.

**File to edit:** `InitialDataChangeset.java` — Implement the migration

```bash
./mvnw test -Dtest="dev.springforge.t3_03.Ex03_MongockMigrationTest"
```

## Check Your Progress
```bash
bash scripts/check-progress.sh t3-03
```

## Key Concepts

### Strict Repository Configuration Mode
When multiple Spring Data modules are on the classpath, Spring uses
**annotations** to determine which module owns which repository:
- `@Entity` → JPA
- `@Document` → MongoDB

Explicit `@Enable*Repositories` annotations remove all ambiguity.

### Cross-Store Transactions
**Not supported.** JPA and MongoDB cannot participate in the same
transaction. Design your application so each operation is complete
within a single data store, or use eventual consistency patterns.

### Mongock vs Flyway
| Concern | Flyway | Mongock |
|---------|--------|---------|
| Database | SQL (any) | MongoDB |
| Migration format | SQL files | Java classes |
| Tracking | flyway_schema_history table | mongockChangeLog collection |
| Annotation | N/A (file-based) | @ChangeUnit (v5+) |
| Rollback | Manual SQL | @RollbackExecution method |
