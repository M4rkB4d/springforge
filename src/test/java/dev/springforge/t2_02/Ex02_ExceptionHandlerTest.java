package dev.springforge.t2_02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * EXERCISE 02: Global Exception Handler
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Create a @RestControllerAdvice that handles exceptions with ProblemDetail.
 *
 * WHAT TO DO:
 * 1. Annotate GlobalExceptionHandler with @RestControllerAdvice
 * 2. Handle TaskNotFoundException → 404 with ProblemDetail
 * 3. Handle MethodArgumentNotValidException → 400 with ProblemDetail
 */
@SpringBootTest(classes = ValidationTestApp.class)
@AutoConfigureMockMvc
@DisplayName("T2-02 Ex02: Exception Handler")
class Ex02_ExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("TaskNotFoundException returns 404 with ProblemDetail")
    void notFoundReturnsProblemDetail() throws Exception {
        mockMvc.perform(get("/api/tasks/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Task Not Found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").exists());
    }

    @Test
    @DisplayName("ProblemDetail contains the correct content type")
    void problemDetailHasCorrectContentType() throws Exception {
        mockMvc.perform(get("/api/tasks/9999"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith("application/problem+json"));
    }
}
