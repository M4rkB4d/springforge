package dev.springforge.t2_03;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("T2-03 Ex01: Advanced MockMvc")
class Ex01_MockMvcAdvancedTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/books creates a book and returns 201 with JSON body")
    void createBookReturns201WithBody() throws Exception {
        String json = """
                {
                    "title": "Spring in Action",
                    "author": "Craig Walls",
                    "isbn": "978-1617297571"
                }
                """;

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Spring in Action"))
                .andExpect(jsonPath("$.author").value("Craig Walls"))
                .andExpect(jsonPath("$.isbn").value("978-1617297571"))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("GET /api/books/{id} returns 404 for non-existent book")
    void getNonExistentBookReturns404() throws Exception {
        mockMvc.perform(get("/api/books/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/books/{id} returns 204 No Content")
    void deleteBookReturns204() throws Exception {
        String json = """
                {
                    "title": "Temp Book",
                    "author": "Temp Author",
                    "isbn": "000-0000000000"
                }
                """;

        String response = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse().getContentAsString();

        JsonNode node = objectMapper.readTree(response);
        long id = node.get("id").asLong();

        mockMvc.perform(delete("/api/books/" + id))
                .andExpect(status().isNoContent());
    }
}
