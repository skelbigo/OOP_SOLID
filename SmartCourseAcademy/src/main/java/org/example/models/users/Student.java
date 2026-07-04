package org.example.models.users;

import org.example.observer.Observer;
import org.example.observer.Subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student implements Observer {
    private final String name;
    private final List<String> notifications;
    private Subject currentCourse;

    public Student(String name) {
        if (name == null || name.strip().isBlank()) {
            throw new IllegalArgumentException("Student name cannot be null or blank");
        }
        this.name = name.strip();
        this.notifications = new ArrayList<>();
    }

    public void enroll(Subject course) {
        if (course == null) {
            throw new IllegalArgumentException("Course can not be null");
        }

        if (this.currentCourse != null) {
            this.currentCourse.unsubscribe(this);
        }

        this.currentCourse = course;
        this.currentCourse.subscribe(this);
    }

    public String getName() {
        return name;
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    @Override
    public void update(String courseName, String message) {
        notifications.add(String.format("[%s] %s: %s", name, courseName, message));
    }
}
