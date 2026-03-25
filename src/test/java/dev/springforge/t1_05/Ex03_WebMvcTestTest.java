package dev.springforge.t1_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercise 3: Testing with @WebMvcTest + MockMvc
 * ★★★☆☆ Practice
 *
 * @WebMvcTest loads ONLY the web layer — no database, no full context.
 * Dependencies are mocked with @MockitoBean.
 * MockMvc lets you test HTTP without starting a real server.
 *
 * YOUR TASK: Write MockMvc test methods for the InfoController.
 */
@WebMvcTest(InfoController.class)
@DisplayName("T1-05 Ex03: @WebMvcTest + MockMvc")
class Ex03_WebMvcTestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @DisplayName("GET /info should return app info as JSON")
    void infoEndpointReturnsJson() throws Exception {
        // TODO: Use mockMvc.perform(get("/info"))
        //   .andExpect(status().isOk())
        //   .andExpect(jsonPath("$.app").value("SpringForge"))
        //   .andExpect(jsonPath("$.status").value("running"));
        throw new UnsupportedOperationException("Write the MockMvc test");
    }

    @Test
    @DisplayName("GET /info/format should use mocked UserService")
    void formatEndpointUsesMock() throws Exception {
        // TODO:
        // 1. Set up the mock: given(userService.formatUsername("Test User")).willReturn("test_user");
        // 2. Perform GET /info/format?username=Test User
        // 3. Assert status 200 and jsonPath("$.formatted").value("test_user")
        throw new UnsupportedOperationException("Write the MockMvc test with mock");
    }
}
