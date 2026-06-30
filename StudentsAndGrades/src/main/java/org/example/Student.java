package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private final String name;
    private final List<Integer> grades;

    public List<Integer> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    public String getName() {
        return name;
    }

    public Student(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name of student can not be blank or null");
        }
        this.name = name.strip();
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        if (grade < 1 || grade > 12) {
            throw new IllegalArgumentException("Grade must be from 1 to 12.");
        }
        grades.add(grade);
    }

    public double getAverageScore() {
        if (grades.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public boolean hasPassed() {
        return getAverageScore() >= 6;
    }
}
