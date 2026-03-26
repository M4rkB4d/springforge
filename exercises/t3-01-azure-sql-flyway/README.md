# T3-01: Azure SQL & Flyway Migrations

## What You'll Learn
- Connecting Spring Boot to Azure SQL (MS SQL Server)
- Writing Flyway migrations in T-SQL (SQL Server syntax)
- Key differences between PostgreSQL and SQL Server
- Using Testcontainers with MS SQL Server

## Prerequisites
- T2-04: JPA & Spring Data
- T2-06: Database Migrations
- Docker running (SQL Server container is ~1.5GB, first pull takes a few minutes)

## Key Concepts

### SQL Server vs PostgreSQL Syntax
| PostgreSQL | SQL Server (T-SQL) |
|---|---|
| `BIGSERIAL` | `BIGINT IDENTITY(1,1)` |
| `BOOLEAN` | `BIT` (0/1) |
| `TIMESTAMP` | `DATETIME2` |
| `VARCHAR(n)` | `NVARCHAR(n)` (Unicode) |
| `NOW()` | `GETUTCDATE()` |
| `TEXT` | `NVARCHAR(MAX)` |

### Testcontainers MSSQL
Unlike PostgreSQL, SQL Server requires EULA acceptance:
```java
new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2022-latest")
    .acceptLicense();  // Required!
```

## Exercises

### Ex01: MSSQL Connection (Worked Example)
Demonstrates connecting to SQL Server via Testcontainers.
Run the tests to see them pass — study how the config works.

### Ex02: Employee Repository on MSSQL
Complete the `Employee` entity annotations, then run the tests.
The schema is created by Flyway migrations (not Hibernate ddl-auto).

### Ex03: Write Your Own T-SQL Migration
Create `V3__add_departments_table.sql` in `src/main/resources/db/mssql/`.
See the test file for exact requirements.

## Running Tests
```bash
./mvnw test -Dtest="dev/springforge/t3_01/**"
```

## Check Progress
```bash
bash scripts/check-progress.sh t3-01
```
