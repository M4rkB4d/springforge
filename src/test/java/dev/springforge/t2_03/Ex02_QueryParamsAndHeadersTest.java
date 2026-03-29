package dev.springforge.t2_03;

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
@DisplayName("T2-03 Ex02: Query Params & Headers")
class Ex02_QueryParamsAndHeadersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/books/search?author=X filters by author")
    void searchByAuthorFilters() throws Exception {
        // Create two books with different authors
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"title": "Book A", "author": "Alice Smith", "isbn": "111"}
                        """));

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"title": "Book B", "author": "Bob Jones", "isbn": "222"}
                        """));

        mockMvc.perform(get("/api/books/search").param("author", "Alice Smith"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].author").value("Alice Smith"));
    }

    @Test
    @DisplayName("POST response has Content-Type application/json")
    void postResponseHasJsonContentType() throws Exception {
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title": "Test", "author": "Test", "isbn": "333"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/books/search returns empty list when no match")
    void searchReturnsEmptyWhenNoMatch() throws Exception {
        mockMvc.perform(get("/api/books/search").param("author", "Nonexistent Author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
