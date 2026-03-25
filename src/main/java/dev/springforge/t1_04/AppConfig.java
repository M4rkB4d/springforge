package dev.springforge.t1_04;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Exercise 1: Configuration Properties Binding
 *
 * Spring Boot binds application.yml values to Java objects automatically.
 * This record maps to the "app" prefix in application.yml.
 *
 * YOUR TASK:
 * 1. Add @ConfigurationProperties(prefix = "app") annotation
 * 2. The record components map to app.name, app.version, app.debug in application.yml
 *
 * You'll also need to add these to application.yml:
 *   app:
 *     name: SpringForge
 *     version: 1.0.0
 *     debug: false
 */
// TODO: Add @ConfigurationProperties(prefix = "app")
public record AppConfig(String name, String version, boolean debug) {
}
