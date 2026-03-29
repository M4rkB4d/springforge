package dev.springforge.t1_05;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Exercise 3: Controller to test with @WebMvcTest
 *
 * This controller is already implemented. Your job is to test it
 * using MockMvc — testing the HTTP layer without starting a full server.
 */
@RestController
public class InfoController {

    private final UserService userService;

    public InfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
                "app", "SpringForge",
                "status", "running"
        );
    }

    @GetMapping("/info/format")
    public Map<String, String> formatUsername(@RequestParam String username) {
        String formatted = userService.formatUsername(username);
        return Map.of("formatted", formatted);
    }
}
