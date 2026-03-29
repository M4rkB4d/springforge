package dev.springforge.t3_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * EXERCISE 01: Security Filter Chain
 *
 * DIFFICULTY: ★★★★☆
 *
 * YOUR TASK:
 * In SecurityConfig.java, configure the SecurityFilterChain so that:
 *   - /api/public/** endpoints are accessible without authentication
 *   - /api/secure/** endpoints require authentication
 *   - The API is stateless (no sessions)
 *   - OAuth2 resource server with JWT is enabled
 *
 * These tests verify the security rules are correctly applied.
 */
@WebMvcTest({PublicController.class, SecureController.class})
@Import(SecurityConfig.class)
@ActiveProfiles("jwt")
@DisplayName("T3-04 Ex01: Security Filter Chain")
class Ex01_SecurityFilterChainTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Public health endpoint is accessible without auth")
    void publicHealthNoAuth() throws Exception {
        mockMvc.perform(get("/api/public/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    @DisplayName("Public info endpoint is accessible without auth")
    void publicInfoNoAuth() throws Exception {
        mockMvc.perform(get("/api/public/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }

    @Test
    @DisplayName("Secure endpoint returns 401 without auth")
    void secureEndpointRequiresAuth() throws Exception {
        mockMvc.perform(get("/api/secure/dashboard"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Secure profile returns 401 without auth")
    void secureProfileRequiresAuth() throws Exception {
        mockMvc.perform(get("/api/secure/profile"))
                .andExpect(status().isUnauthorized());
    }
}
