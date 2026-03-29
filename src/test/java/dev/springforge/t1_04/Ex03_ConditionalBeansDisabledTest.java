package dev.springforge.t1_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Same exercise, but proves the bean is ABSENT when condition is false.
 */
@SpringBootTest(classes = ConfigTestApp.class)
@TestPropertySource(properties = "app.audit.enabled=false")
@DisplayName("T1-04 Ex03b: Conditional Beans (disabled)")
class Ex03_ConditionalBeansDisabledTest {

    @Autowired(required = false)
    private String auditMessage;

    @Test
    @DisplayName("Audit bean should NOT exist when app.audit.enabled=false")
    void auditBeanAbsentWhenDisabled() {
        assertThat(auditMessage).isNull();
    }
}
