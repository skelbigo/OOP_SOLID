package org.example.strategy;

import org.example.models.Task;

import java.util.List;

public interface TaskSortStrategy {
    void sort(List<Task> tasks);
}
