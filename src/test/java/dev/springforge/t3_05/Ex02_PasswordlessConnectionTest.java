package dev.springforge.t3_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: Passwordless Connection Pattern
 *
 * DIFFICULTY: ★★★★☆
 *
 * YOUR TASKS:
 * In PasswordlessDataSourceConfig.java:
 *   1. Return "ActiveDirectoryDefault" from getAuthenticationMethod()
 *   2. Return false from requiresCredentials()
 *
 * This verifies you understand the Managed Identity pattern:
 *   - No passwords stored in config or code
 *   - Azure handles authentication via identity tokens
 *   - Same code works locally (Azure CLI) and in production (Managed Identity)
 */
@SpringBootTest(classes = AzureConfigTestApp.class)
@DisplayName("T3-05 Ex02: Passwordless Connection")
class Ex02_PasswordlessConnectionTest {

    @Autowired
    private PasswordlessDataSourceConfig config;

    @Test
    @DisplayName("Authentication method is ActiveDirectoryDefault")
    void correctAuthMethod() {
        assertThat(config.getAuthenticationMethod())
                .as("auth method should be ActiveDirectoryDefault")
                .isEqualTo("ActiveDirectoryDefault");
    }

    @Test
    @DisplayName("Managed Identity does not require username/password")
    void noCredentialsRequired() {
        assertThat(config.requiresCredentials()).as("managed identity needs no credentials").isFalse();
    }
}
