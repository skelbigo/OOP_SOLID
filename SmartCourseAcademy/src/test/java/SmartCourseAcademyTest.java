import org.example.factory.CourseFactory;
import org.example.factory.CourseType;
import org.example.models.courses.Course;
import org.example.models.users.Student;
import org.example.strategies.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartCourseAcademyTest {
    private CourseFactory factory;
    private Student sasha;
    private Student valya;

    @BeforeEach
    void setUp() {
        factory = new CourseFactory();
        sasha = new Student("Sasha");
        valya = new Student("Valya");
    }

    @Test
    void shouldHandleFullCourseLifecycleWithTests() {
        Course javaCourse = factory.createCourse(CourseType.JAVA, new TestStrategy());

        sasha.enroll(javaCourse);
        valya.enroll(javaCourse);

        javaCourse.publishLesson("Generics");
        javaCourse.announceDeadline("Generics Quiz", "Tomorrow");

        javaCourse.gradeStudent(sasha, 95);
        javaCourse.gradeStudent(valya, 40);

        assertEquals(3, sasha.getNotifications().size());
        assertTrue(sasha.getNotifications().get(0).contains("New lesson published - Generics"));
        assertTrue(sasha.getNotifications().get(1).contains("Deadline for 'Generics Quiz' is Tomorrow"));
        assertTrue(sasha.getNotifications().get(2).contains("Grade received: Test Passed (95 pts)"));

        assertTrue(valya.getNotifications().get(2).contains("Grade received: Test Failed (40 pts)"));
    }

    @Test
    void studentShouldOnlyReceiveNotificationsFromCurrentCourse() {
        Course pythonCourse = factory.createCourse(CourseType.PYTHON, new PracticalStrategy());
        Course qaCourse = factory.createCourse(CourseType.QA, new FinalProjectStrategy());

        sasha.enroll(pythonCourse);
        pythonCourse.publishLesson("Decorators");
        assertEquals(1, sasha.getNotifications().size());

        sasha.enroll(qaCourse);

        pythonCourse.publishLesson("Generators");

        qaCourse.publishLesson("Selenium");

        assertEquals(2, sasha.getNotifications().size());
        assertTrue(sasha.getNotifications().get(1).contains("[Sasha] QA Course: New lesson published - Selenium"));
    }

    @Test
    void gradingUnenrolledStudentShouldThrowException() {
        Course javaCourse = factory.createCourse(CourseType.JAVA, new TestStrategy());
        assertThrows(IllegalArgumentException.class, () -> javaCourse.gradeStudent(sasha, 100));
    }

    @Test
    void shouldThrowExceptionForInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> factory.createCourse(null, new TestStrategy()));
        assertThrows(IllegalArgumentException.class, () -> factory.createCourse(CourseType.JAVA, null));
        assertThrows(IllegalArgumentException.class, () -> new Student(null));
        assertThrows(IllegalArgumentException.class, () -> sasha.enroll(null));
    }
}
