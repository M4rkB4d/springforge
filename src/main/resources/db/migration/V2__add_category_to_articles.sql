-- V2: Add category column to articles
-- This is a migration exercise — the learner will write V3.

ALTER TABLE articles ADD COLUMN category VARCHAR(100);
