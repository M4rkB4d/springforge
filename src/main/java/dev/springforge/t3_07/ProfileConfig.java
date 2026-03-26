package dev.springforge.t3_07;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Exercise 1: Spring Profiles for Environment Configuration
 *
 * YOUR TASKS:
 * 1. Add @Profile("dev") to DevConfig inner class
 * 2. Add @Profile("prod") to ProdConfig inner class
 * 3. Inject the "app.environment" property in each
 *
 * HOW PROFILES WORK WITH AZURE:
 * Set SPRING_PROFILES_ACTIVE=prod as an environment variable
 * in Azure App Service or Container Apps. Spring Boot loads
 * application-prod.yml automatically.
 *
 * Typical profile setup:
 *   application.yml          — shared defaults
 *   application-dev.yml      — local development (H2, debug logging)
 *   application-staging.yml  — staging (Azure SQL, reduced logging)
 *   application-prod.yml     — production (Azure SQL, Application Insights)
 */
@Configuration
public class ProfileConfig {

    // TODO: Add @Profile("dev")
    @Configuration
    public static class DevConfig {
        @Value("${app.environment:unknown}")
        private String environment;

        public String getEnvironment() { return environment; }
    }

    // TODO: Add @Profile("prod")
    @Configuration
    public static class ProdConfig {
        @Value("${app.environment:unknown}")
        private String environment;

        public String getEnvironment() { return environment; }
    }
}
