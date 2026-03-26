package dev.springforge.t2_03;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A complete controller for testing exercises.
 * This is ALREADY IMPLEMENTED — your job is to write the TESTS.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final Map<Long, Book> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @GetMapping
    public List<Book> listBooks() {
        return new ArrayList<>(store.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return Optional.ofNullable(store.get(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest request) {
        Long id = sequence.getAndIncrement();
        Book book = new Book(id, request.title(), request.author(), request.isbn());
        store.put(id, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (store.remove(id) != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String author) {
        return store.values().stream()
                .filter(b -> b.author().equalsIgnoreCase(author))
                .toList();
    }

    public record Book(Long id, String title, String author, String isbn) {}
    public record CreateBookRequest(String title, String author, String isbn) {}
}
