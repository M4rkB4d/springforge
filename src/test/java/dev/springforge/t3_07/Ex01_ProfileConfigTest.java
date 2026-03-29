package dev.springforge.t3_07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: Spring Profiles
 *
 * YOUR TASKS:
 * In ProfileConfig.java:
 *   1. Add @Profile("dev") to DevConfig
 *   2. Add @Profile("prod") to ProdConfig
 *
 * This test activates the "dev" profile and verifies that only
 * DevConfig is loaded (not ProdConfig).
 */
@SpringBootTest(classes = DeploymentTestApp.class, properties = "app.environment=development")
@ActiveProfiles("dev")
@DisplayName("T3-07 Ex01: Spring Profiles")
class Ex01_ProfileConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("DevConfig is active when dev profile is set")
    void devConfigActive() {
        assertThat(context.containsBean("profileConfig.DevConfig"))
                .as("DevConfig should be loaded with dev profile")
                .isTrue();
    }

    @Test
    @DisplayName("ProdConfig is NOT active when dev profile is set")
    void prodConfigNotActive() {
        assertThat(context.containsBean("profileConfig.ProdConfig"))
                .as("ProdConfig should NOT be loaded with dev profile")
                .isFalse();
    }

    @Test
    @DisplayName("Environment property is injected correctly")
    void environmentInjected() {
        ProfileConfig.DevConfig devConfig =
                context.getBean(ProfileConfig.DevConfig.class);
        assertThat(devConfig.getEnvironment()).as("environment should be 'development'").isEqualTo("development");
    }
}
