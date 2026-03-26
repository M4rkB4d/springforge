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

/**
 * EXERCISE 02: Query Parameters and Response Headers
 *
 * DIFFICULTY: ★★★☆☆
 *
 * Test the search endpoint and verify response headers.
 *
 * WHAT TO DO:
 * 1. Test GET /api/books/search?author=... returns filtered results
 * 2. Test response Content-Type header is application/json
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("T2-03 Ex02: Query Params & Headers")
class Ex02_QueryParamsAndHeadersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/books/search?author=X filters by author")
    void searchByAuthorFilters() throws Exception {
        // TODO: Create 2 books (different authors), then search for one author
        // Assert: only matching books returned
        // Hint: Use .param("author", "Author Name") in the request builder
        throw new UnsupportedOperationException("Write the test");
    }

    @Test
    @DisplayName("POST response has Content-Type application/json")
    void postResponseHasJsonContentType() throws Exception {
        // TODO: Create a book and verify the Content-Type header
        // Hint: .andExpect(header().string("Content-Type", ...))
        //   or .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        throw new UnsupportedOperationException("Write the test");
    }

    @Test
    @DisplayName("GET /api/books/search returns empty list when no match")
    void searchReturnsEmptyWhenNoMatch() throws Exception {
        // TODO: Search for an author that doesn't exist
        // Assert: 200 OK with empty JSON array
        throw new UnsupportedOperationException("Write the test");
    }
}
