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
 * EXERCISE 03: Role-Based Access Control
 *
 * YOUR TASK:
 * In SecureController.java, add @PreAuthorize("hasAuthority('SCOPE_admin')")
 * to the /api/secure/admin endpoint.
 *
 * HOW ROLES WORK WITH ENTRA ID:
 * Azure Entra ID issues JWTs with a "scope" or "roles" claim.
 * Spring Security maps JWT scopes to authorities with a "SCOPE_" prefix.
 * So a JWT scope of "admin" becomes authority "SCOPE_admin".
 *
 * In production, you configure App Roles in the Entra ID app registration
 * and assign them to users/groups. The JWT automatically includes them.
 *
 * @PreAuthorize checks the authority BEFORE the method executes.
 * If the user lacks the required authority, Spring returns 403 Forbidden.
 */
@WebMvcTest({PublicController.class, SecureController.class})
@Import(SecurityConfig.class)
@ActiveProfiles("jwt")
@DisplayName("T3-04 Ex03: Role-Based Access Control")
class Ex03_RoleBasedAccessTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Admin endpoint accessible with admin scope")
    void adminWithAdminScope() throws Exception {
        mockMvc.perform(get("/api/secure/admin")
                        .with(jwt().jwt(token -> token
                                .subject("admin-user")
                                .claim("scope", "admin read write"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Welcome, admin!"));
    }

    @Test
    @DisplayName("Admin endpoint returns 403 without admin scope")
    void adminWithoutAdminScope() throws Exception {
        mockMvc.perform(get("/api/secure/admin")
                        .with(jwt().jwt(token -> token
                                .subject("regular-user")
                                .claim("scope", "read write"))))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Regular user can still access dashboard")
    void regularUserCanAccessDashboard() throws Exception {
        mockMvc.perform(get("/api/secure/dashboard")
                        .with(jwt().jwt(token -> token
                                .subject("regular-user")
                                .claim("scope", "read"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("regular-user"));
    }
}
