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
 * EXERCISE 01: Bean Validation
 *
 * DIFFICULTY: ★★☆☆☆
 *
 * OBJECTIVE: Add validation annotations to CreateTaskRequest and @Valid to the controller.
 *
 * WHAT TO DO:
 * 1. Add @NotBlank to title, @Size(max=500) to description, @Min(1) @Max(10) to priority
 * 2. Add @Valid before @RequestBody in TaskController.createTask()
 */
@SpringBootTest(classes = ValidationTestApp.class)
@AutoConfigureMockMvc
@DisplayName("T2-02 Ex01: Bean Validation")
class Ex01_ValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Valid task is created successfully (201)")
    void validTaskReturns201() throws Exception {
        String json = """
                {"title": "Learn Spring", "description": "Complete T2-02", "priority": 5}
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Learn Spring"));
    }

    @Test
    @DisplayName("Blank title returns 400 Bad Request")
    void blankTitleReturns400() throws Exception {
        String json = """
                {"title": "", "description": "No title", "priority": 5}
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Priority below 1 returns 400 Bad Request")
    void priorityTooLowReturns400() throws Exception {
        String json = """
                {"title": "Valid title", "description": "desc", "priority": 0}
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Priority above 10 returns 400 Bad Request")
    void priorityTooHighReturns400() throws Exception {
        String json = """
                {"title": "Valid title", "description": "desc", "priority": 11}
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
