-- V3: Create departments table with seed data

CREATE TABLE departments (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL UNIQUE,
    budget DECIMAL(15,2),
    created_at DATETIME2 NOT NULL DEFAULT GETUTCDATE()
);

INSERT INTO departments (name, budget) VALUES (N'Engineering', 500000.00);
INSERT INTO departments (name, budget) VALUES (N'Marketing', 250000.00);
INSERT INTO departments (name, budget) VALUES (N'Finance', 300000.00);
