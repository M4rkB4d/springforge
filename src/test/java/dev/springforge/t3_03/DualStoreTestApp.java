package dev.springforge.t3_03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Minimal application context for dual store tests.
 * Only scans t3_03 to avoid cross-tier bean collisions.
 */
@SpringBootApplication(scanBasePackages = "dev.springforge.t3_03")
public class DualStoreTestApp {
}
