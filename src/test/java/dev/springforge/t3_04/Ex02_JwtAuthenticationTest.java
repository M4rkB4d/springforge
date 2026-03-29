package dev.springforge.t3_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * EXERCISE 02: JWT Authentication
 *
 * DIFFICULTY: ★★★★☆
 *
 * YOUR TASK:
 * In SecureController.java, implement the /api/secure/profile endpoint:
 *   - Extract "sub", "name", "email" claims from the JWT
 *   - Return them as a Map with keys "userId", "name", "email"
 *
 * These tests use Spring Security's mock JWT support to simulate
 * Azure Entra ID tokens. In production, the tokens come from
 * Entra ID's /authorize and /token endpoints.
 *
 * HOW MOCK JWT WORKS:
 * The jwt() post-processor creates a fake JWT with the claims
 * you specify. Your SecurityFilterChain validates it the same
 * way it would validate a real Entra ID token.
 */
@WebMvcTest({PublicController.class, SecureController.class})
@Import(SecurityConfig.class)
@ActiveProfiles("jwt")
@DisplayName("T3-04 Ex02: JWT Authentication")
class Ex02_JwtAuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Authenticated user can access secure dashboard")
    void authenticatedAccessToDashboard() throws Exception {
        mockMvc.perform(get("/api/secure/dashboard")
                        .with(jwt().jwt(token -> token
                                .subject("user-123")
                                .claim("scope", "read write"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("user-123"));
    }

    @Test
    @DisplayName("Profile endpoint extracts JWT claims correctly")
    void profileExtractsJwtClaims() throws Exception {
        mockMvc.perform(get("/api/secure/profile")
                        .with(jwt().jwt(token -> token
                                .subject("user-456")
                                .claim("name", "Jane Doe")
                                .claim("email", "jane@springforge.dev"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user-456"))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane@springforge.dev"));
    }

    @Test
    @DisplayName("JWT subject is available via @AuthenticationPrincipal")
    void jwtSubjectAvailable() throws Exception {
        mockMvc.perform(get("/api/secure/dashboard")
                        .with(jwt().jwt(token -> token
                                .subject("entra-id-object-id-789")
                                .claim("scope", "profile"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("entra-id-object-id-789"));
    }
}
