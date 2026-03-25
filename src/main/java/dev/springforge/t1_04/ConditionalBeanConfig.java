package dev.springforge.t1_04;

import org.springframework.context.annotation.Configuration;

/**
 * Exercise 3: @Bean and @ConditionalOnProperty
 *
 * Spring auto-configuration uses conditions to decide which beans to create.
 * You can do the same with @ConditionalOnProperty.
 *
 * YOUR TASK:
 * 1. Annotate class with @Configuration
 * 2. Create a @Bean method that returns a String "Audit logging enabled"
 * 3. Add @ConditionalOnProperty(name = "app.audit.enabled", havingValue = "true")
 *    so the bean only exists when auditing is turned on
 *
 * Hint: The bean method should return a String and be named auditMessage().
 */
// TODO: Add @Configuration
public class ConditionalBeanConfig {

    // TODO: Add @Bean and @ConditionalOnProperty
    public String auditMessage() {
        return "Audit logging enabled";
    }
}
