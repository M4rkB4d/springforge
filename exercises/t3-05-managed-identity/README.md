# T3-05: Managed Identity + Key Vault

## What You'll Learn
- Azure Key Vault secrets as Spring properties
- Passwordless connections to Azure SQL via Managed Identity
- DefaultAzureCredential — how it works locally and in production

## Exercises

### Exercise 1: Key Vault Integration
**File to edit:** `KeyVaultConfig.java` — Add @Value annotations

```bash
./mvnw test -Dtest="dev.springforge.t3_05.Ex01_KeyVaultIntegrationTest"
```

### Exercise 2: Passwordless Connection Pattern
**File to edit:** `PasswordlessDataSourceConfig.java`

```bash
./mvnw test -Dtest="dev.springforge.t3_05.Ex02_PasswordlessConnectionTest"
```

## Key Concepts

### DefaultAzureCredential Chain
1. Environment variables (CI/CD)
2. Workload Identity (AKS)
3. Managed Identity (App Service, Container Apps)
4. Azure CLI (`az login` — local dev)
5. IntelliJ/VS Code Azure plugins

Same code works everywhere. Zero credentials in config files.
