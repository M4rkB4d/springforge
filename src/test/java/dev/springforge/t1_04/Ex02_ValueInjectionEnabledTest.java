package dev.springforge.t1_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Same exercise, but with the property explicitly set to true.
 * This proves @Value actually reads the property, not just the default.
 */
@SpringBootTest(classes = ConfigTestApp.class)
@TestPropertySource(properties = "app.feature.beta-enabled=true")
@DisplayName("T1-04 Ex02b: @Value with property set")
class Ex02_ValueInjectionEnabledTest {

    @Autowired
    private FeatureToggle featureToggle;

    @Test
    @DisplayName("FeatureToggle should read true when property is set")
    void propertyOverridesDefault() {
        assertThat(featureToggle.isBetaEnabled()).as("beta should be true when property is set").isTrue();
    }
}
