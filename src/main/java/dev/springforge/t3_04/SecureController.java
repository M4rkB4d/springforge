package dev.springforge.t3_04;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Exercise 2 & 3: Secure API — authentication required.
 *
 * YOUR TASKS (Exercise 2):
 * The /api/secure/profile endpoint should extract claims from the JWT:
 *   - "sub" (subject — the user ID)
 *   - "name" (display name)
 *   - "email" (email address)
 * Use jwt.getClaimAsString("claim_name") to extract each claim.
 *
 * YOUR TASKS (Exercise 3):
 * The /api/secure/admin endpoint requires the "ADMIN" role.
 * Add @PreAuthorize("hasAuthority('SCOPE_admin')") to restrict access.
 *
 * HOW JWT CLAIMS WORK WITH ENTRA ID:
 * When a user authenticates via Azure Entra ID, they receive a JWT
 * containing claims like "sub", "name", "email", "roles", etc.
 * Your API validates this token and extracts the claims to identify
 * the user and their permissions.
 */
@RestController
@RequestMapping("/api/secure")
public class SecureController {

    @GetMapping("/profile")
    public Map<String, Object> profile(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "userId", jwt.getClaimAsString("sub"),
                "name", jwt.getClaimAsString("name"),
                "email", jwt.getClaimAsString("email")
        );
    }

    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @GetMapping("/admin")
    public Map<String, String> adminOnly(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "message", "Welcome, admin!",
                "user", jwt.getSubject()
        );
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "user", jwt.getSubject(),
                "scopes", jwt.getClaimAsStringList("scope"),
                "message", "Authenticated dashboard access"
        );
    }
}
