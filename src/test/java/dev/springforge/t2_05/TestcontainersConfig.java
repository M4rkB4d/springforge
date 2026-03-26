package dev.springforge.t2_05;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Exercise 1: Testcontainers Configuration
 *
 * YOUR TASKS:
 * 1. This @TestConfiguration creates a PostgreSQL container for tests
 * 2. The @ServiceConnection annotation tells Spring Boot to auto-configure
 *    the DataSource from this container — no manual property wiring needed
 *
 * STUDY this class. You'll write a similar one in Ex02.
 * This is a WORKED EXAMPLE — already complete.
 */
@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfig {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:16-alpine");
    }
}
