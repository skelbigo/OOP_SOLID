package org.example.models.courses;

import org.example.strategies.EvaluationStrategy;

public class PythonCourse extends Course {
    public PythonCourse(EvaluationStrategy strategy) {
        super("Python Course", strategy);
    }
}