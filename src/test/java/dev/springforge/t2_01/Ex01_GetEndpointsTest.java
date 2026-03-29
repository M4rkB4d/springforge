package dev.springforge.t2_01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * EXERCISE 01: GET Endpoints
 *
 * DIFFICULTY: ★★☆☆☆
 *
 * OBJECTIVE: Create GET endpoints for listing and retrieving products.
 *
 * WHAT TO DO:
 * 1. Add @RestController and @RequestMapping("/api/products") to ProductController
 * 2. Inject ProductRepository via constructor
 * 3. Implement GET /api/products (list all)
 * 4. Implement GET /api/products/{id} (get one, return 404 if not found)
 */
@SpringBootTest(classes = RestTestApp.class)
@AutoConfigureMockMvc
@DisplayName("T2-01 Ex01: GET Endpoints")
class Ex01_GetEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @Test
    @DisplayName("GET /api/products returns empty list initially")
    void listProductsReturnsEmptyList() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("GET /api/products returns all products")
    void listProductsReturnsAll() throws Exception {
        repository.save(new Product(null, "Keyboard", "Mechanical", 79.99));
        repository.save(new Product(null, "Mouse", "Wireless", 49.99));

        try {
            mockMvc.perform(get("/api/products"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThanOrEqualTo(2)));
        } finally {
            // Clean up for test isolation
            repository.findAll().forEach(p -> repository.deleteById(p.id()));
        }
    }

    @Test
    @DisplayName("GET /api/products/{id} returns product when found")
    void getProductReturnsProduct() throws Exception {
        Product saved = repository.save(new Product(null, "Monitor", "4K", 399.99));

        try {
            mockMvc.perform(get("/api/products/" + saved.id()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Monitor"))
                    .andExpect(jsonPath("$.price").value(399.99));
        } finally {
            repository.deleteById(saved.id());
        }
    }

    @Test
    @DisplayName("GET /api/products/{id} returns 404 when not found")
    void getProductReturns404WhenNotFound() throws Exception {
        mockMvc.perform(get("/api/products/9999"))
                .andExpect(status().isNotFound());
    }
}
