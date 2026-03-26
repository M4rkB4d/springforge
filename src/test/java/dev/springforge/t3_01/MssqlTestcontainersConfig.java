package dev.springforge.t3_01;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MSSQLServerContainer;

/**
 * Testcontainers configuration for Azure SQL (MS SQL Server).
 *
 * KEY DIFFERENCES FROM POSTGRESQL TESTCONTAINERS:
 * 1. .acceptLicense() is REQUIRED — SQL Server has a EULA
 * 2. Image is ~1.5GB (vs ~300MB for PostgreSQL) — first pull is slow
 * 3. Container uses port 1433 (vs 5432 for PostgreSQL)
 * 4. @ServiceConnection auto-wires the datasource — same as PostgreSQL
 */
@TestConfiguration(proxyBeanMethods = false)
public class MssqlTestcontainersConfig {

    @Bean
    @ServiceConnection
    MSSQLServerContainer<?> sqlServerContainer() {
        return new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2022-latest")
                .acceptLicense();
    }
}
