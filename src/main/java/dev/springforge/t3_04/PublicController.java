package dev.springforge.t3_04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Public API — no authentication required.
 * This controller is already complete.
 */
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "springforge");
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of("version", "1.0.0", "environment", "development");
    }
}
