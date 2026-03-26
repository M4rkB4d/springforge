-- V2: Add audit columns to employees table
--
-- YOUR TASK: Write V3 migration (see exercise README)
-- This migration demonstrates ALTER TABLE in T-SQL syntax.

ALTER TABLE dbo.employees
    ADD created_at DATETIME2 NOT NULL DEFAULT GETUTCDATE(),
        updated_at DATETIME2 NOT NULL DEFAULT GETUTCDATE();
