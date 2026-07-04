package org.example.observer;

import org.example.models.Task;

public interface TaskObserver {
    void onTaskDone(Task task);
}
