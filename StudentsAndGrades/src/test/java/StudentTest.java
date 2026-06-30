import org.example.Student;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student student;
    private Student student1;

    @BeforeEach
    void createStudent() {
        student = new Student("Oleksandra");
        student.addGrade(10);
        student.addGrade(12);
        student1 = new Student("     Oleksiy    ");
    }

    @Test
    void shouldThrowExceptionWhenNameIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Student(null));
        assertThrows(IllegalArgumentException.class, () -> new Student(""));
        assertThrows(IllegalArgumentException.class, () -> new Student(" "));

    }

    @Test
    void shouldCreateStudentAndTrimName() {
        assertEquals("Oleksiy", student1.getName(), "The name must not have spaces at the beginning or end");
        assertTrue(student1.getGrades().isEmpty(), "A new student does not need to have any grades");
    }

    @Test
    void shouldThrowExceptionForInvalidGrades() {
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(0), "The score must be greater than 0");
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(13), "The score must be less than 13");
    }

    @Test
    void shouldAddValidGrades() {
        student.addGrade(11);
        assertEquals(3.0, student.getGrades().size(), "Three grades must be saved");
        assertTrue(student.getGrades().contains(10));
        assertTrue(student.getGrades().contains(12));
        assertTrue(student.getGrades().contains(11));
    }

    @Test
    void shouldProtectGradesListFromModification() {
        List<Integer> grades = student.getGrades();
        assertThrows(UnsupportedOperationException.class, () -> grades.add(12), "Any attempt to modify the list from outside should result in an error");
    }

    @Test
    void shouldCalculateAverageScoreCorrectly() {
        assertEquals(11.0, student.getAverageScore(), 0.0001);
        student.addGrade(6);
        assertEquals(9.3333, student.getAverageScore(), 0.0001);
    }

    @Test
    void shouldReturnZeroForAverageScoreWhenNoGrades() {
        assertEquals(0.0, student1.getAverageScore(), 0.0001, "If there are no grades, the average score should be 0");
    }

    @Test
    void shouldCorrectlyDetermineIfStudentPassed() {
        Student boundaryStudent = new Student("Boundary");
        boundaryStudent.addGrade(5);
        boundaryStudent.addGrade(7);
        assertTrue(boundaryStudent.hasPassed(), "A student with exact average of 6.0 must pass");

        Student failingStudent = new Student("Failing");
        failingStudent.addGrade(5);
        failingStudent.addGrade(6);
        assertFalse(failingStudent.hasPassed(), "A student with average below 6.0 must fail");

        Student excellentStudent = new Student("Excellent");
        excellentStudent.addGrade(10);
        excellentStudent.addGrade(12);
        assertTrue(excellentStudent.hasPassed(), "A student with average well above 6.0 must pass");
    }
}
