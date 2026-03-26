package dev.springforge.t1_02;

/**
 * Exercise 1 & 2: Your First REST Controller
 *
 * YOUR TASKS:
 *
 * Ex01: Create a GET endpoint at "/hello" that returns "Hello, SpringForge!"
 *   Hint: You need @RestController on the class and @GetMapping("/hello") on the method.
 *
 * Ex02: Create a GET endpoint at "/hello/{name}" that returns "Hello, {name}!"
 *   Hint: Use @PathVariable to capture the {name} from the URL.
 */
// TODO: Add @RestController annotation
public class HelloController {

    // TODO: Add @GetMapping("/hello")
    public String hello() {
        // TODO: Return "Hello, SpringForge!"
        throw new UnsupportedOperationException("Implement hello()");
    }

    // TODO: Add @GetMapping("/hello/{name}")
    public String helloName(String name) {
        // TODO: Add @PathVariable annotation to name parameter
        // TODO: Return "Hello, " + name + "!"
        throw new UnsupportedOperationException("Implement helloName()");
    }
}
