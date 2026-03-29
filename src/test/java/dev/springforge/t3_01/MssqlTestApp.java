package dev.springforge.t3_01;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Minimal application context for MSSQL Testcontainer tests.
 * Only scans t3_01 to avoid cross-tier bean collisions.
 */
@SpringBootApplication(scanBasePackages = "dev.springforge.t3_01")
public class MssqlTestApp {
}
