package dev.springforge.t3_03;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

/**
 * Exercise 1: MongoDB Document — AuditLog (stored in MongoDB)
 *
 * YOUR TASKS:
 * 1. Add @Document(collection = "audit_logs") to the class
 * 2. Add @Field("account_id") to the accountId field
 * 3. Add @Field("action") to the action field
 *
 * This document lives in MongoDB while Account lives in SQL.
 * That's the dual data store pattern — each database handles
 * what it's best at. SQL for structured relational data,
 * MongoDB for flexible, high-volume event logs.
 */
// TODO: Add @Document(collection = "audit_logs")
public class AuditLog {

    @Id
    private String id;

    // TODO: Add @Field("account_id")
    private Long accountId;

    // TODO: Add @Field("action")
    private String action;

    private String details;

    private Instant timestamp;

    protected AuditLog() {}

    public AuditLog(Long accountId, String action, String details) {
        this.accountId = accountId;
        this.action = action;
        this.details = details;
        this.timestamp = Instant.now();
    }

    public String getId() { return id; }
    public Long getAccountId() { return accountId; }
    public String getAction() { return action; }
    public String getDetails() { return details; }
    public Instant getTimestamp() { return timestamp; }
}
