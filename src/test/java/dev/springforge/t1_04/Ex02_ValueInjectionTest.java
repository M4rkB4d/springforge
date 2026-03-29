package dev.springforge.t1_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: @Value Injection
 *
 * DIFFICULTY: ★★☆☆☆
 *
 * Faded Example
 *
 * For single properties, @Value is simpler than @ConfigurationProperties.
 * Default values prevent crashes when properties are missing.
 *
 * YOUR TASK: Make FeatureToggle a @Component with @Value on betaEnabled.
 */
@SpringBootTest(classes = ConfigTestApp.class)
@DisplayName("T1-04 Ex02: @Value Injection")
class Ex02_ValueInjectionTest {

    @Autowired
    private FeatureToggle featureToggle;

    @Test
    @DisplayName("FeatureToggle should use default value (false) when property not set")
    void defaultValueUsed() {
        assertThat(featureToggle.isBetaEnabled()).as("beta should default to false when unset").isFalse();
    }
}
