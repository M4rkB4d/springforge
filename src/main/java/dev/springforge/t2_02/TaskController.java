package dev.springforge.t2_02;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Exercise 1 & 2: Task Controller with Validation
 *
 * YOUR TASKS:
 *
 * Ex01 — Add @Valid to the request body parameter in createTask().
 *   Without @Valid, Spring ignores your validation annotations.
 *   With @Valid, Spring returns 400 Bad Request automatically on violations.
 *
 * Ex02 — The getTask() method throws TaskNotFoundException.
 *   You'll handle this in GlobalExceptionHandler (Ex02).
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    // TODO: Ex01 — Add @Valid before @RequestBody
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest request) {
        Task task = new Task(null, request.title(), request.description(), request.priority());
        Task saved = repository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(repository.findAll());
    }
}
