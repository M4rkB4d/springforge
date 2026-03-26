package dev.springforge.t3_02;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;

/**
 * Testcontainers configuration for MongoDB.
 *
 * Unlike MSSQL, MongoDB doesn't require .acceptLicense().
 * @ServiceConnection auto-wires spring.data.mongodb.uri.
 */
@TestConfiguration(proxyBeanMethods = false)
public class MongoTestcontainersConfig {

    @Bean
    @ServiceConnection
    MongoDBContainer mongoContainer() {
        return new MongoDBContainer("mongo:7.0");
    }
}
