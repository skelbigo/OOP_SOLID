package org.example.factory;

import org.example.models.courses.Course;
import org.example.models.courses.JavaCourse;
import org.example.models.courses.PythonCourse;
import org.example.models.courses.QACourse;
import org.example.strategies.EvaluationStrategy;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class CourseFactory {
    private final Map<CourseType, Function<EvaluationStrategy, Course>> registry;

    public CourseFactory() {
        Map<CourseType, Function<EvaluationStrategy, Course>> temp = new EnumMap<>(CourseType.class);
        temp.put(CourseType.JAVA, JavaCourse::new);
        temp.put(CourseType.PYTHON, PythonCourse::new);
        temp.put(CourseType.QA, QACourse::new);
        this.registry = Collections.unmodifiableMap(temp);
    }

    public Course createCourse(CourseType courseType, EvaluationStrategy evaluationStrategy) {
        if (courseType == null) {
            throw new IllegalArgumentException("Course type cannot be null");
        }
        if (evaluationStrategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }

        return registry.get(courseType).apply(evaluationStrategy);
    }
}
