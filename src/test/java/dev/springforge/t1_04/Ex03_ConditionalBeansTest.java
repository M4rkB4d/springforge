package dev.springforge.t1_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 03: @ConditionalOnProperty
 *
 * DIFFICULTY: ★★★☆☆
 *
 * Practice
 *
 * Auto-configuration's secret: beans only exist when conditions are met.
 * This is how Spring Boot configures hundreds of things without you asking.
 *
 * YOUR TASK: Add @Bean + @ConditionalOnProperty to ConditionalBeanConfig.
 */
@SpringBootTest(classes = ConfigTestApp.class)
@TestPropertySource(properties = "app.audit.enabled=true")
@DisplayName("T1-04 Ex03: Conditional Beans (enabled)")
class Ex03_ConditionalBeansTest {

    @Autowired(required = false)
    private String auditMessage;

    @Test
    @DisplayName("Audit bean should exist when app.audit.enabled=true")
    void auditBeanExistsWhenEnabled() {
        assertThat(auditMessage).as("audit bean should exist when enabled").isNotNull();
        assertThat(auditMessage).as("audit bean message").isEqualTo("Audit logging enabled");
    }
}
