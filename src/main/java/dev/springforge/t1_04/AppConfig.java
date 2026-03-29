package dev.springforge.t1_04;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Exercise 1: Configuration Properties Binding — SOLUTION
 */
@ConfigurationProperties(prefix = "app")
public record AppConfig(String name, String version, boolean debug) {
}
