package org.example.models;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private final String id;
    private final String name;
    private final Priority priority;
    private final LocalDate deadline;
    private TaskStatus taskStatus;

    public Task(String name, Priority priority, LocalDate deadline) {
        if (name == null || name.strip().isBlank()) {
            throw new IllegalArgumentException("Task name cannot be blank");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        if (deadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }
        this.id = UUID.randomUUID().toString();
        this.name = name.strip();
        this.priority = priority;
        this.deadline = deadline;
        this.taskStatus = TaskStatus.TODO;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Priority: %s, Deadline: %s)", taskStatus, name, priority, deadline);
    }
}
