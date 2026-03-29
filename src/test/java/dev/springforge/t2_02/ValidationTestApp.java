package dev.springforge.t2_02;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Scoped test application for T2-02 validation exercises.
 * Permits all requests — security is covered in T3-04.
 */
@SpringBootApplication(scanBasePackages = "dev.springforge.t2_02")
public class ValidationTestApp {

    @Bean
    SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();
    }
}
