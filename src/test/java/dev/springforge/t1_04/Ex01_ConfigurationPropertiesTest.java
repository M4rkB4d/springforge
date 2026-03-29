package dev.springforge.t1_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 1: @ConfigurationProperties
 * ★★☆☆☆ Faded Example
 *
 * Spring Boot maps YAML/properties to Java objects automatically.
 * No manual parsing, no getString() calls — just annotate and use.
 *
 * YOUR TASK:
 * 1. Add @ConfigurationProperties(prefix = "app") to AppConfig
 * 2. Add @EnableConfigurationProperties(AppConfig.class) or @ConfigurationPropertiesScan
 *    to your main application class
 * 3. Add app.name, app.version, app.debug to application.yml
 */
@SpringBootTest(classes = ConfigTestApp.class)
@DisplayName("T1-04 Ex01: Configuration Properties")
class Ex01_ConfigurationPropertiesTest {

    @Autowired
    private AppConfig appConfig;

    @Test
    @DisplayName("AppConfig should bind app.name from YAML")
    void appNameBound() {
        assertThat(appConfig.name()).isEqualTo("SpringForge");
    }

    @Test
    @DisplayName("AppConfig should bind app.version from YAML")
    void appVersionBound() {
        assertThat(appConfig.version()).isEqualTo("1.0.0");
    }

    @Test
    @DisplayName("AppConfig should bind app.debug from YAML")
    void appDebugBound() {
        assertThat(appConfig.debug()).isFalse();
    }
}
