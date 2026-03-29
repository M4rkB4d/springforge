package dev.springforge.t3_05;

import org.springframework.context.annotation.Configuration;

/**
 * Exercise 2: Passwordless Connection to Azure SQL
 *
 * YOUR TASKS:
 * 1. Understand the connection string format for passwordless auth:
 *
 *    spring.datasource.url=jdbc:sqlserver://server.database.windows.net:1433;\
 *      databaseName=mydb;authentication=ActiveDirectoryDefault;
 *
 *    NOTE: No username or password properties needed!
 *
 * 2. Understand what authentication=ActiveDirectoryDefault does:
 *    - Uses DefaultAzureCredential from the Azure Identity SDK
 *    - On Azure: automatically uses the app's Managed Identity
 *    - Locally: falls back to Azure CLI credentials (az login)
 *
 * 3. Understand the Azure-side setup required:
 *    - Enable Entra ID admin on the Azure SQL server
 *    - CREATE USER [app-identity-name] FROM EXTERNAL PROVIDER
 *    - ALTER ROLE db_datareader ADD MEMBER [app-identity-name]
 *    - ALTER ROLE db_datawriter ADD MEMBER [app-identity-name]
 *
 * This is a CONCEPTUAL exercise — the test verifies your understanding
 * of the passwordless authentication pattern rather than connecting
 * to an actual Azure SQL instance.
 */
@Configuration
public class PasswordlessDataSourceConfig {

    /**
     * Returns the JDBC authentication parameter for Managed Identity.
     *
     * TODO: Return "ActiveDirectoryDefault" instead of the placeholder.
     * This is the mssql-jdbc 12.2+ parameter name for DefaultAzureCredential.
     */
    public String getAuthenticationMethod() {
        return "ActiveDirectoryDefault";
    }

    /**
     * Returns whether username/password is required for Managed Identity auth.
     */
    public boolean requiresCredentials() {
        return false;
    }
}
