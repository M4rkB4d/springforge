# T3-07: Azure Deployment (Profiles + Graceful Shutdown)

## What You'll Learn
- Spring profiles for environment-specific configuration
- Graceful shutdown for zero-downtime deployments
- Azure Container Apps deployment patterns

## Prerequisites
- T1-04 (Auto-Configuration — profile loading fundamentals)

## Exercises

### Exercise 1: Spring Profiles ★★☆☆☆
**File to edit:** `ProfileConfig.java` — Add @Profile annotations

Use `@Profile` to create environment-specific beans. The test activates
the "dev" profile and verifies only DevConfig loads (not ProdConfig).

```bash
./mvnw test -Dtest="dev.springforge.t3_07.Ex01_ProfileConfigTest"
```

### Exercise 2: Graceful Shutdown ★★☆☆☆
**File to edit:** `GracefulShutdownConfig.java`

Configure graceful shutdown so in-flight requests complete before
the app stops — critical for zero-downtime deployments on Azure.

```bash
./mvnw test -Dtest="dev.springforge.t3_07.Ex02_GracefulShutdownTest"
```

## Check Your Progress
```bash
bash scripts/check-progress.sh t3-07
```

## Azure Container Apps Deployment

### Why Azure Container Apps (Not Azure Spring Apps)
Azure Spring Apps was retired March 2025. Azure Container Apps
is the recommended replacement — fully managed, serverless
containers with auto-scaling and KEDA support.

### Deployment Pipeline (GitHub Actions)
1. Checkout → Setup Java → Maven build
2. Build Docker image → Push to Azure Container Registry
3. Deploy to Container Apps via `azure/container-apps-deploy`

### Environment Variables on Azure
```bash
# Set active profile
az containerapp update --name myapp \
  --set-env-vars SPRING_PROFILES_ACTIVE=prod

# Application Insights (use connection string, NOT instrumentation key)
az containerapp update --name myapp \
  --set-env-vars APPLICATIONINSIGHTS_CONNECTION_STRING=InstrumentationKey=...
```
