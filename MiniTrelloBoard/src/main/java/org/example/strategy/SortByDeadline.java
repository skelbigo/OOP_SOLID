package org.example.strategy;

import org.example.models.Task;

import java.util.Comparator;
import java.util.List;

public class SortByDeadline implements TaskSortStrategy {
    public void sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getDeadline));
    }
}
