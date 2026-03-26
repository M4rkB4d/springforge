-- V1: Create employees table (T-SQL / Azure SQL syntax)
--
-- KEY DIFFERENCES FROM POSTGRESQL:
-- ┌──────────────────────┬────────────────────────────────┐
-- │ PostgreSQL           │ SQL Server (T-SQL)              │
-- ├──────────────────────┼────────────────────────────────┤
-- │ BIGSERIAL            │ BIGINT IDENTITY(1,1)           │
-- │ BOOLEAN              │ BIT                            │
-- │ TIMESTAMP            │ DATETIME2                      │
-- │ VARCHAR(n)           │ NVARCHAR(n) for Unicode        │
-- │ TEXT                 │ NVARCHAR(MAX)                  │
-- └──────────────────────┴────────────────────────────────┘

CREATE TABLE dbo.employees (
    id          BIGINT IDENTITY(1,1) PRIMARY KEY,
    first_name  NVARCHAR(100)   NOT NULL,
    last_name   NVARCHAR(100)   NOT NULL,
    email       NVARCHAR(150)   NOT NULL,
    department  NVARCHAR(100),
    salary      DECIMAL(10,2),
    hire_date   DATE,
    active      BIT             NOT NULL DEFAULT 1,

    CONSTRAINT uq_employees_email UNIQUE (email)
);

-- Index for common queries
CREATE INDEX idx_employees_department ON dbo.employees (department);
CREATE INDEX idx_employees_active ON dbo.employees (active) WHERE active = 1;
