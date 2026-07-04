package org.example.models.courses;

import org.example.strategies.EvaluationStrategy;

public class QACourse extends Course {
    public QACourse(EvaluationStrategy evaluationStrategy) {
        super("QA Course", evaluationStrategy);
    }
}
