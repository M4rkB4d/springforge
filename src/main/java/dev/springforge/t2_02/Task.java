package dev.springforge.t2_02;

public record Task(Long id, String title, String description, int priority) {
    public Task withId(Long newId) {
        return new Task(newId, title, description, priority);
    }
}
