package org.example.models.courses;

import org.example.observer.Observer;
import org.example.observer.Subject;
import org.example.strategies.EvaluationStrategy;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Course implements Subject {
    private final String name;
    private final EvaluationStrategy strategy;
    private final Set<Observer> students = new LinkedHashSet<>();

    public Course(String name, EvaluationStrategy strategy) {
        if (name == null || name.strip().isBlank()) {
            throw new IllegalArgumentException("The name of course can not be null or blank");
        }
        if (strategy == null) {
            throw new IllegalArgumentException("Evaluation strategy cannot be null");
        }
        this.name = name.strip();
        this.strategy = strategy;
    }

    @Override
    public void subscribe(Observer observer) {
        students.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        students.remove(observer);
    }

    @Override
    public String getCourseName() {
        return name;
    }

    public void publishLesson(String lessonName) {
        notifyAllStudents("New lesson published - " + lessonName);
    }

    public void announceDeadline(String task, String date) {
        notifyAllStudents("Deadline for '" + task + "' is " + date);
    }

    public void gradeStudent(Observer student, int score) {
        if (!students.contains(student)) {
            throw new IllegalArgumentException("Student is not enrolled in this course");
        }
        String evaluationResult = strategy.evaluate(score);
        student.update(name, "Grade received: " + evaluationResult);
    }

    private void notifyAllStudents(String message) {
        for (Observer student : students) {
            student.update(name, message);
        }
    }
}
