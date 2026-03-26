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
 * EXERCISE 01: Advanced MockMvc — Write the Tests
 *
 * DIFFICULTY: ★★☆☆☆
 *
 * The BookController is already implemented. YOUR TASK: write MockMvc tests.
 *
 * WHAT TO DO:
 * 1. Complete each test method by replacing the throw with MockMvc assertions
 * 2. Use the patterns from the T1-05 guide
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("T2-03 Ex01: Advanced MockMvc")
class Ex01_MockMvcAdvancedTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/books creates a book and returns 201 with JSON body")
    void createBookReturns201WithBody() throws Exception {
        // TODO: POST a JSON body with title, author, isbn
        // Assert: status 201, jsonPath $.title, $.author, $.isbn, $.id exists
        throw new UnsupportedOperationException("Write the test");
    }

    @Test
    @DisplayName("GET /api/books/{id} returns 404 for non-existent book")
    void getNonExistentBookReturns404() throws Exception {
        // TODO: GET /api/books/9999
        // Assert: status 404
        throw new UnsupportedOperationException("Write the test");
    }

    @Test
    @DisplayName("DELETE /api/books/{id} returns 204 No Content")
    void deleteBookReturns204() throws Exception {
        // TODO: First create a book (POST), extract the ID from the response,
        //       then DELETE it and assert 204
        // Hint: Use mockMvc.perform(post(...)).andReturn().getResponse().getContentAsString()
        //       Parse the ID with ObjectMapper or jsonPath
        throw new UnsupportedOperationException("Write the test");
    }
}
