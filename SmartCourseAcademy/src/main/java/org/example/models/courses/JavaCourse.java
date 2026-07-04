package org.example.models.courses;

import org.example.strategies.EvaluationStrategy;

public class JavaCourse extends Course {
    public JavaCourse(EvaluationStrategy evaluationStrategy) {
        super("Java Course", evaluationStrategy);
    }
}
