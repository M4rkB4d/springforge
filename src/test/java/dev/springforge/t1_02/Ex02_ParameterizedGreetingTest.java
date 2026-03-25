package dev.springforge.t1_02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * EXERCISE 02: Parameterized Greeting
 *
 * DIFFICULTY: ★★☆☆☆ (Faded Example)
 * BLOOM'S: Apply
 *
 * OBJECTIVE: Create a REST endpoint that accepts a path variable.
 *
 * WHAT TO DO:
 * 1. Open HelloController.java
 * 2. Add a new method that handles GET /hello/{name}
 * 3. It should return "Hello, {name}!" where {name} comes from the URL
 *
 * HINTS (reveal progressively):
 * - Hint 1: Use @GetMapping("/hello/{name}")
 * - Hint 2: Add @PathVariable String name as a method parameter
 * - Hint 3: Return "Hello, " + name + "!"
 *
 * GUIDE REFERENCE: SpringForge Guide T1-02, "Try It" section
 */
@WebMvcTest(HelloController.class)
class Ex02_ParameterizedGreetingTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /hello/Mark returns personalized greeting")
    void shouldReturnPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/hello/Mark"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Mark!"));
    }

    @Test
    @DisplayName("GET /hello/World returns greeting for World")
    void shouldReturnGreetingForWorld() throws Exception {
        mockMvc.perform(get("/hello/World"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    @DisplayName("GET /hello/SpringForge returns greeting for SpringForge")
    void shouldReturnGreetingForSpringForge() throws Exception {
        mockMvc.perform(get("/hello/SpringForge"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, SpringForge!"));
    }
}
