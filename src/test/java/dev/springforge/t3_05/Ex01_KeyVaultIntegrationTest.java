package dev.springforge.t3_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: Key Vault Integration
 *
 * YOUR TASK:
 * In KeyVaultConfig.java, add @Value annotations to inject secrets.
 * The test uses default values since we're not connected to a real vault.
 *
 * In production, these defaults would be overridden by actual
 * Key Vault secrets loaded via spring-cloud-azure-starter-keyvault-secrets.
 */
@SpringBootTest(properties = {
        "db-password=test-db-password-from-vault",
        "api-key=test-api-key-from-vault"
})
@DisplayName("T3-05 Ex01: Key Vault Integration")
class Ex01_KeyVaultIntegrationTest {

    @Autowired
    private KeyVaultConfig keyVaultConfig;

    @Test
    @DisplayName("db-password secret is injected from properties")
    void dbPasswordInjected() {
        assertThat(keyVaultConfig.getDbPassword())
                .isEqualTo("test-db-password-from-vault");
    }

    @Test
    @DisplayName("api-key secret is injected from properties")
    void apiKeyInjected() {
        assertThat(keyVaultConfig.getApiKey())
                .isEqualTo("test-api-key-from-vault");
    }
}
