package dev.springforge.t2_02;

/**
 * Custom exception for when a task is not found.
 * This is already implemented — used by the controller and tested in Ex02.
 */
public class TaskNotFoundException extends RuntimeException {

    private final Long taskId;

    public TaskNotFoundException(Long taskId) {
        super("Task not found with id: " + taskId);
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }
}
