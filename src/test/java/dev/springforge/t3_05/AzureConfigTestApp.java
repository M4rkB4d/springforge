package dev.springforge.t3_05;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Minimal application context for Azure configuration tests.
 * Only scans t3_05 to avoid cross-tier bean collisions.
 */
@SpringBootApplication(scanBasePackages = "dev.springforge.t3_05")
public class AzureConfigTestApp {
}
