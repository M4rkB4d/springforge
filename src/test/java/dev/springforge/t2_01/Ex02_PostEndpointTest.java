package dev.springforge.t2_01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * EXERCISE 02: POST Endpoint
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Create a POST endpoint that accepts JSON and creates a product.
 *
 * WHAT TO DO:
 * 1. Add @PostMapping to a method that accepts @RequestBody CreateProductRequest
 * 2. Save the product using the repository
 * 3. Return 201 Created with the saved product in the response body
 */
@SpringBootTest(classes = RestTestApp.class)
@AutoConfigureMockMvc
@DisplayName("T2-01 Ex02: POST Endpoint")
class Ex02_PostEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @Test
    @DisplayName("POST /api/products creates product and returns 201")
    void createProductReturns201() throws Exception {
        String json = """
                {
                    "name": "Webcam",
                    "description": "1080p HD",
                    "price": 59.99
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Webcam"))
                .andExpect(jsonPath("$.description").value("1080p HD"))
                .andExpect(jsonPath("$.price").value(59.99))
                .andExpect(jsonPath("$.id").isNumber());

        // Clean up
        repository.findAll().stream()
                .filter(p -> "Webcam".equals(p.name()))
                .forEach(p -> repository.deleteById(p.id()));
    }

    @Test
    @DisplayName("POST /api/products assigns an ID to the new product")
    void createProductAssignsId() throws Exception {
        String json = """
                {
                    "name": "Headphones",
                    "description": "Noise cancelling",
                    "price": 199.99
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNotEmpty());

        // Clean up
        repository.findAll().stream()
                .filter(p -> "Headphones".equals(p.name()))
                .forEach(p -> repository.deleteById(p.id()));
    }
}
