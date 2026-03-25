package dev.springforge.t1_05;

import org.springframework.stereotype.Service;

/**
 * Exercise 2: Service to test with @SpringBootTest
 *
 * This service is already implemented. Your job is to test it
 * using Spring's testing support.
 */
@Service
public class UserService {

    public String formatUsername(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        return raw.trim().toLowerCase().replaceAll("\\s+", "_");
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
