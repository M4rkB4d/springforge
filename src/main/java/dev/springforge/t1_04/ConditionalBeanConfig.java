package dev.springforge.t1_04;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Exercise 3: @Bean and @ConditionalOnProperty — SOLUTION
 */
@Configuration
public class ConditionalBeanConfig {

    @Bean
    @ConditionalOnProperty(name = "app.audit.enabled", havingValue = "true")
    public String auditMessage() {
        return "Audit logging enabled";
    }
}
