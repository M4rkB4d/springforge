package dev.springforge.t3_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Exercise 1 & 2: Security Configuration
 *
 * YOUR TASKS:
 * 1. Configure the SecurityFilterChain:
 *    - Disable CSRF (stateless API — no browser sessions)
 *    - Set session management to STATELESS
 *    - Permit all requests to "/api/public/**"
 *    - Require authentication for "/api/secure/**"
 *    - Configure OAuth2 resource server with JWT support
 *
 * HINTS:
 *   http.csrf(csrf -> csrf.disable())
 *       .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 *       .authorizeHttpRequests(auth -> auth
 *           .requestMatchers("/api/public/**").permitAll()
 *           .requestMatchers("/api/secure/**").authenticated()
 *           .anyRequest().permitAll())
 *       .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
 *
 * WHY STATELESS:
 * REST APIs don't use server-side sessions. Each request carries
 * its own authentication (JWT token in Authorization header).
 * This is how Azure Entra ID works — it issues JWT tokens that
 * your API validates on every request.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: Configure the security filter chain
        // See hints above for the required configuration
        throw new UnsupportedOperationException("Configure the SecurityFilterChain");
    }
}
