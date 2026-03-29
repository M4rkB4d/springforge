package dev.springforge.t2_01;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Scoped test application for T2-01 REST controller exercises.
 * Permits all requests — security is covered in T3-04.
 */
@SpringBootApplication(scanBasePackages = "dev.springforge.t2_01")
public class RestTestApp {

    @Bean
    SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();
    }
}
