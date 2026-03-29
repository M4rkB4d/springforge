package dev.springforge.t2_01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * EXERCISE 03: PUT & DELETE Endpoints
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Complete the CRUD with update and delete operations.
 *
 * WHAT TO DO:
 * 1. PUT /api/products/{id} — accept CreateProductRequest, update existing, return 200
 *    Return 404 if the product doesn't exist
 * 2. DELETE /api/products/{id} — delete product, return 204 No Content
 *    Return 404 if the product doesn't exist
 */
@SpringBootTest(classes = RestTestApp.class)
@AutoConfigureMockMvc
@DisplayName("T2-01 Ex03: PUT & DELETE Endpoints")
class Ex03_PutDeleteEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    private Product existingProduct;

    @BeforeEach
    void setUp() {
        existingProduct = repository.save(new Product(null, "Laptop", "16 inch", 1299.99));
    }

    @AfterEach
    void tearDown() {
        repository.findAll().forEach(p -> repository.deleteById(p.id()));
    }

    @Test
    @DisplayName("PUT /api/products/{id} updates existing product")
    void updateProductReturns200() throws Exception {
        String json = """
                {
                    "name": "Laptop Pro",
                    "description": "18 inch M4",
                    "price": 1999.99
                }
                """;

        mockMvc.perform(put("/api/products/" + existingProduct.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop Pro"))
                .andExpect(jsonPath("$.price").value(1999.99))
                .andExpect(jsonPath("$.id").value(existingProduct.id()));
    }

    @Test
    @DisplayName("PUT /api/products/{id} returns 404 for non-existent product")
    void updateNonExistentProductReturns404() throws Exception {
        String json = """
                {
                    "name": "Ghost",
                    "description": "Does not exist",
                    "price": 0.00
                }
                """;

        mockMvc.perform(put("/api/products/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/products/{id} deletes product and returns 204")
    void deleteProductReturns204() throws Exception {
        mockMvc.perform(delete("/api/products/" + existingProduct.id()))
                .andExpect(status().isNoContent());

        assertThat(repository.findById(existingProduct.id())).as("deleted product should not exist in repo").isEmpty();
    }

    @Test
    @DisplayName("DELETE /api/products/{id} returns 404 for non-existent product")
    void deleteNonExistentProductReturns404() throws Exception {
        mockMvc.perform(delete("/api/products/9999"))
                .andExpect(status().isNotFound());
    }
}
