package dev.springforge.t1_03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: Component Scanning & @Service
 *
 * DIFFICULTY: ★☆☆☆☆
 *
 * Worked Example
 *
 * Spring scans your packages for @Component, @Service, @Repository, @Controller.
 * When it finds one, it creates a bean and manages its lifecycle.
 *
 * YOUR TASK: Add @Service to SimpleGreetingService and implement greet().
 */
@SpringBootTest(classes = DiTestApp.class)
@DisplayName("T1-03 Ex01: Component Scanning")
class Ex01_ComponentScanningTest {

    @Autowired
    private GreetingService greetingService;

    @Test
    @DisplayName("Spring should discover and inject SimpleGreetingService")
    void springDiscoversService() {
        assertThat(greetingService).as("service should be injected").isNotNull();
        assertThat(greetingService).as("should be SimpleGreetingService").isInstanceOf(SimpleGreetingService.class);
    }

    @Test
    @DisplayName("greet() should return formatted greeting")
    void greetReturnsFormattedGreeting() {
        String result = greetingService.greet("Mark");
        assertThat(result).as("greet('Mark') result").isEqualTo("Hello, Mark!");
    }

    @Test
    @DisplayName("greet() should work with different names")
    void greetWorksWithDifferentNames() {
        assertThat(greetingService.greet("Spring")).as("greet('Spring') result").isEqualTo("Hello, Spring!");
        assertThat(greetingService.greet("Boot")).as("greet('Boot') result").isEqualTo("Hello, Boot!");
    }
}
