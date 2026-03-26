package dev.springforge.t3_03;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MSSQLServerContainer;

/**
 * Testcontainers configuration that starts BOTH SQL Server and MongoDB.
 *
 * This is the dual data store test setup. In production, you'd have
 * separate Azure SQL and MongoDB (or Cosmos DB) instances. For testing,
 * Testcontainers spins up both in Docker containers.
 *
 * @ServiceConnection on each container auto-wires:
 *   - SQL Server → spring.datasource.*
 *   - MongoDB → spring.data.mongodb.*
 */
@TestConfiguration(proxyBeanMethods = false)
public class DualStoreTestConfig {

    @Bean
    @ServiceConnection
    MSSQLServerContainer<?> sqlServerContainer() {
        return new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2022-latest")
                .acceptLicense();
    }

    @Bean
    @ServiceConnection
    MongoDBContainer mongoContainer() {
        return new MongoDBContainer("mongo:7.0");
    }
}
