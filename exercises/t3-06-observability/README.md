# T3-06: Observability (Health Checks + HikariCP Tuning)

## What You'll Learn
- Custom health indicators for Azure probes
- HikariCP connection pool tuning for Azure SQL
- Why max-lifetime and keepalive-time matter in cloud

## Exercises

### Exercise 1: Custom Health Indicator
**File to edit:** `HealthConfig.java`

```bash
./mvnw test -Dtest="dev.springforge.t3_06.Ex01_HealthIndicatorTest"
```

### Exercise 2: HikariCP Tuning for Azure SQL
**File to edit:** `HikariTuningConfig.java`

```bash
./mvnw test -Dtest="dev.springforge.t3_06.Ex02_HikariTuningTest"
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
      probes.enabled: true

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
