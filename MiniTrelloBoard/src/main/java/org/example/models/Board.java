package org.example.models;

import org.example.iterator.BoardIterator;
import org.example.observer.TaskObserver;
import org.example.strategy.TaskSortStrategy;

import java.util.*;

public class Board implements Iterable<Task> {
    private final Map<TaskStatus, List<Task>> columns = new EnumMap<>(TaskStatus.class);
    private final Set<TaskObserver> observers = new LinkedHashSet<>();

    public Board() {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            columns.put(taskStatus, new ArrayList<>());
        }
    }

    public void subscribe(TaskObserver taskObserver) {
        if (taskObserver == null) {
            throw new IllegalArgumentException("Task observer cannot be null");
        }
        observers.add(taskObserver);
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (isTaskOnBoard(task)) {
            throw new IllegalArgumentException("Task is already on the board");
        }
        columns.get(TaskStatus.TODO).add(task);
    }

    public void moveTask(Task task, TaskStatus newStatus) {
        if (task == null || newStatus == null) throw new IllegalArgumentException("Invalid arguments");

        TaskStatus oldStatus = task.getTaskStatus();
        if (oldStatus == newStatus) return;

        List<Task> currentColumn = columns.get(oldStatus);

        if (!currentColumn.contains(task)) {
            throw new IllegalArgumentException("Task not found in its expected column on the board");
        }

        currentColumn.remove(task);
        task.setTaskStatus(newStatus);
        columns.get(newStatus).add(task);

        if (newStatus == TaskStatus.DONE) {
            notifyObservers(task);
        }
    }

    public void sortColumn(TaskStatus taskStatus, TaskSortStrategy taskSortStrategy) {
        if (taskStatus == null || taskSortStrategy == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        List<Task> column = columns.get(taskStatus);
        taskSortStrategy.sort(column);
    }

    public List<Task> getColumn(TaskStatus taskStatus) {
        if (taskStatus == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }
        return Collections.unmodifiableList(columns.get(taskStatus));
    }

    private void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.onTaskDone(task);
        }
    }

    private boolean isTaskOnBoard(Task task) {
        for (List<Task> column : columns.values()) {
            if (column.contains(task)) return true;
        }
        return false;
    }

    @Override
    public Iterator<Task> iterator() {
        return new BoardIterator(this);
    }
}
