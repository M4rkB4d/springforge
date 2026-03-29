# T3-05: Managed Identity + Key Vault

## What You'll Learn
- Azure Key Vault secrets injected as Spring `@Value` properties
- Passwordless connections to Azure SQL via Managed Identity
- DefaultAzureCredential — how it works locally and in production
- Why zero-credential configuration is the gold standard for cloud apps

## Prerequisites
- T3-01 (Azure SQL & Flyway Migrations)
- T3-04 (Spring Security + JWT)

## Why This Matters

In traditional apps, database passwords live in config files, environment
variables, or — worst case — source code. Every secret is a liability:
leaked, rotated too slowly, or shared across environments.

Azure Managed Identity eliminates all of this. Your app authenticates to
Azure SQL, Key Vault, and other services using identity tokens — no
passwords stored anywhere. The same code works locally (Azure CLI)
and in production (Managed Identity).

## Exercises

### Exercise 1: Key Vault Integration ★★☆☆☆
**File to edit:** `KeyVaultConfig.java`

Add `@Value` annotations to inject secrets from Key Vault properties.
In production, `spring-cloud-azure-starter-keyvault-secrets` maps
Key Vault secrets to Spring properties automatically.

The test uses default values since we're not connected to a real vault.

```bash
./mvnw test -Dtest="dev.springforge.t3_05.Ex01_KeyVaultIntegrationTest"
```

### Exercise 2: Passwordless Connection Pattern ★★☆☆☆
**File to edit:** `PasswordlessDataSourceConfig.java`

Configure the authentication method and verify that Managed Identity
connections don't require username/password credentials.

```bash
./mvnw test -Dtest="dev.springforge.t3_05.Ex02_PasswordlessConnectionTest"
```

## Check Your Progress
```bash
bash scripts/check-progress.sh t3-05
```

## Key Concepts

### DefaultAzureCredential Chain
Azure's `DefaultAzureCredential` tries authentication methods in order:
1. **Environment variables** (CI/CD pipelines)
2. **Workload Identity** (AKS pods)
3. **Managed Identity** (App Service, Container Apps)
4. **Azure CLI** (`az login` — local development)
5. **IntelliJ/VS Code** Azure plugins

Same code works everywhere. Zero credentials in config files.

### How Key Vault Integration Works
```
Key Vault secret "db-password"
    ↓ spring-cloud-azure-starter-keyvault-secrets
Spring property "db-password"
    ↓ @Value("${db-password}")
Your Java code
```

### Production vs Local Development
| Environment | Auth Method | How It Works |
|------------|-------------|--------------|
| App Service | Managed Identity | Azure assigns identity to your app automatically |
| Local dev | Azure CLI | `az login` provides your developer identity |
| CI/CD | Environment vars | Service principal credentials in pipeline secrets |
