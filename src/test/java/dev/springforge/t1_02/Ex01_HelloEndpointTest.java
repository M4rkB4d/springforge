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
 * EXERCISE 01: Hello, SpringForge!
 *
 * DIFFICULTY: ★☆☆☆☆ (Worked Example)
 * BLOOM'S: Apply
 *
 * OBJECTIVE: Create your first REST endpoint.
 *
 * WHAT TO DO:
 * 1. Open HelloController.java
 * 2. Add @RestController annotation to the class
 * 3. Create a method that returns "Hello, SpringForge!"
 * 4. Annotate it with @GetMapping("/hello")
 *
 * HINTS (reveal progressively):
 * - Hint 1: @RestController goes on the class declaration
 * - Hint 2: The method signature is: public String hello()
 * - Hint 3: Use @GetMapping("/hello") above the method
 *
 * GUIDE REFERENCE: SpringForge Guide T1-02, "Worked Example" section
 */
@WebMvcTest(HelloController.class)
class Ex01_HelloEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /hello returns 200 OK with greeting")
    void shouldReturnHelloGreeting() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, SpringForge!"));
    }

    @Test
    @DisplayName("GET /hello returns plain text content")
    void shouldReturnPlainText() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/plain"));
    }
}
