package dev.springforge.t3_05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Exercise 1: Key Vault Integration
 *
 * YOUR TASKS:
 * 1. Inject the "db-password" secret using @Value("${db-password:default-password}")
 * 2. Inject the "api-key" secret using @Value("${api-key:default-key}")
 * 3. Create getter methods for both secrets
 *
 * HOW KEY VAULT WORKS WITH SPRING BOOT:
 * With spring-cloud-azure-starter-keyvault-secrets, secrets from
 * Azure Key Vault appear as Spring properties. No special code needed.
 *
 * Configuration in application.yml:
 *   spring.cloud.azure.keyvault.secret.property-sources[0].endpoint=
 *     https://your-vault.vault.azure.net/
 *
 * When deployed with Managed Identity, no credentials are needed.
 * DefaultAzureCredential handles authentication automatically:
 *   - On Azure: uses the app's managed identity
 *   - Locally: uses your Azure CLI login (az login)
 */
@Configuration
public class KeyVaultConfig {

    // TODO: @Value("${db-password:default-password}")
    private String dbPassword = "default-password";

    // TODO: @Value("${api-key:default-key}")
    private String apiKey = "default-key";

    public String getDbPassword() {
        return dbPassword;
    }

    public String getApiKey() {
        return apiKey;
    }
}
