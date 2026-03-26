package dev.springforge.t2_02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * EXERCISE 03: ProblemDetail with Field Errors
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Make validation errors return ProblemDetail with field-level error details.
 *
 * WHAT TO DO:
 * 1. In GlobalExceptionHandler, handle MethodArgumentNotValidException
 * 2. Build a ProblemDetail with status 400
 * 3. Add field errors as a property: problemDetail.setProperty("errors", errors)
 *    where errors is a map of field name → error message
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("T2-02 Ex03: ProblemDetail Field Errors")
class Ex03_ProblemDetailFieldErrorsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Validation errors return ProblemDetail with errors property")
    void validationErrorsReturnFieldErrors() throws Exception {
        String json = """
                {"title": "", "description": "desc", "priority": 0}
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    @DisplayName("ProblemDetail includes specific field error messages")
    void problemDetailIncludesFieldNames() throws Exception {
        String json = """
                {"title": "", "description": "desc", "priority": 0}
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.title").exists())
                .andExpect(jsonPath("$.errors.priority").exists());
    }
}
