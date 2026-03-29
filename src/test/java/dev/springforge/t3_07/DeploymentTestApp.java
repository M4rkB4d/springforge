package dev.springforge.t3_07;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Minimal application context for deployment configuration tests.
 * Only scans t3_07 to avoid cross-tier bean collisions.
 */
@SpringBootApplication(scanBasePackages = "dev.springforge.t3_07")
public class DeploymentTestApp {
}
