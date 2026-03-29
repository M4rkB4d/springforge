# T3-06: Health Checks & Connection Pooling

## What You'll Learn
- Custom health indicators for Azure liveness and readiness probes
- HikariCP connection pool tuning for Azure SQL
- Why `max-lifetime` and `keepalive-time` matter in cloud environments
- How Actuator health endpoints map to Kubernetes/App Service health probes

## Prerequisites
- T3-01 (Azure SQL & Flyway Migrations)

## Why This Matters

Cloud platforms kill unresponsive containers. Azure App Service and
Kubernetes send health probes to your app — if it doesn't respond
correctly, the platform restarts it. Custom health indicators let you
report the actual health of YOUR dependencies (database, cache, queue),
not just "the JVM is running."

Connection pooling is equally critical. Azure SQL has a 30-minute idle
timeout that silently drops connections. Without proper HikariCP tuning,
your app serves errors on the first request after an idle period.

## Exercises

### Exercise 1: Custom Health Indicator ★★☆☆☆
**File to edit:** `HealthConfig.java`

Create a custom health indicator that reports application-specific
health status. Actuator exposes this at `/actuator/health`.

```bash
./mvnw test -Dtest="dev.springforge.t3_06.Ex01_HealthIndicatorTest"
```

### Exercise 2: HikariCP Tuning for Azure SQL ★★☆☆☆
**File to edit:** `HikariTuningConfig.java`

Configure connection pool settings that survive Azure's 30-minute
idle timeout. These values are critical for production reliability.

```bash
./mvnw test -Dtest="dev.springforge.t3_06.Ex02_HikariTuningTest"
```

## Check Your Progress
```bash
bash scripts/check-progress.sh t3-06
```

## Key Configuration

```yaml
# Actuator health endpoints
management:
  endpoints:
    web.exposure.include: health,info,metrics
  endpoint:
    health:
      show-details: when_authorized
  health:
    probes:
      enabled: true  # enables /actuator/health/liveness + /readiness

# HikariCP for Azure SQL
spring:
  datasource:
    hikari:
      maximum-pool-size: 10
      max-lifetime: 1740000      # 29 min — under Azure's 30-min limit
      keepalive-time: 30000      # 30s — prevent silent connection drops
      connection-timeout: 30000
      connection-test-query: SELECT 1
```

### Why These Specific Values?

| Setting | Value | Reason |
|---------|-------|--------|
| `max-lifetime` | 29 min | Azure SQL drops idle connections at 30 min. Recycle before that. |
| `keepalive-time` | 30s | Send periodic pings to keep connections alive through firewalls. |
| `maximum-pool-size` | 10 | Azure SQL Basic tier allows 30 connections. Leave headroom. |
| `connection-test-query` | `SELECT 1` | Validate connections before handing them to your code. |
