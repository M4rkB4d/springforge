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
 * Exercise 3: Testing with @WebMvcTest + MockMvc — SOLUTION
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
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.app").value("SpringForge"))
                .andExpect(jsonPath("$.status").value("running"));
    }

    @Test
    @DisplayName("GET /info/format should use mocked UserService")
    void formatEndpointUsesMock() throws Exception {
        given(userService.formatUsername("Test User")).willReturn("test_user");

        mockMvc.perform(get("/info/format").param("username", "Test User"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formatted").value("test_user"));
    }
}
