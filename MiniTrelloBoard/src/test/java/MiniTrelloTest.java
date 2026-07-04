import org.example.models.Board;
import org.example.models.Priority;
import org.example.models.Task;
import org.example.models.TaskStatus;
import org.example.observer.EmailNotifier;
import org.example.strategy.SortByDeadline;
import org.example.strategy.SortByPriority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MiniTrelloTest {
    private Board board;
    private EmailNotifier emailNotifier;

    @BeforeEach
    void setUp() {
        board = new Board();
        emailNotifier = new EmailNotifier("pm@company.com");
        board.subscribe(emailNotifier);
    }

    @Test
    void shouldMoveTaskAndNotifyObserversWhenDone() {
        Task task = new Task("Setup Database", Priority.HIGH, LocalDate.now().plusDays(2));
        board.addTask(task);

        board.moveTask(task, TaskStatus.IN_PROGRESS);
        assertTrue(emailNotifier.getSentEmails().isEmpty());

        board.moveTask(task, TaskStatus.DONE);
        assertEquals(1, emailNotifier.getSentEmails().size());
        assertTrue(emailNotifier.getSentEmails().get(0).contains("Sending email to pm@company.com: Task 'Setup Database' is DONE!"));
    }

    @Test
    void shouldSortTasksUsingStrategy() {
        Task taskLow = new Task("Low Priority Task", Priority.LOW, LocalDate.now().plusDays(5));
        Task taskHigh = new Task("High Priority Task", Priority.HIGH, LocalDate.now().plusDays(1));
        Task taskMedium = new Task("Medium Priority Task", Priority.MEDIUM, LocalDate.now().plusDays(3));

        board.addTask(taskLow);
        board.addTask(taskHigh);
        board.addTask(taskMedium);

        board.sortColumn(TaskStatus.TODO, new SortByPriority());
        List<Task> todoList = board.getColumn(TaskStatus.TODO);

        assertEquals(Priority.HIGH, todoList.get(0).getPriority());
        assertEquals(Priority.MEDIUM, todoList.get(1).getPriority());
        assertEquals(Priority.LOW, todoList.get(2).getPriority());

        board.sortColumn(TaskStatus.TODO, new SortByDeadline());
        todoList = board.getColumn(TaskStatus.TODO);

        assertEquals("High Priority Task", todoList.get(0).getName());
        assertEquals("Medium Priority Task", todoList.get(1).getName());
        assertEquals("Low Priority Task", todoList.get(2).getName());
    }

    @Test
    void shouldIterateOverAllTasksAccrossAllColumns() {
        Task t1 = new Task("Task 1", Priority.LOW, LocalDate.now());
        Task t2 = new Task("Task 2", Priority.MEDIUM, LocalDate.now());
        Task t3 = new Task("Task 3", Priority.HIGH, LocalDate.now());

        board.addTask(t1);
        board.addTask(t2);
        board.addTask(t3);

        board.moveTask(t2, TaskStatus.IN_PROGRESS);
        board.moveTask(t3, TaskStatus.DONE);

        List<Task> iteratedTasks = new ArrayList<>();
        for (Task task : board) {
            iteratedTasks.add(task);
        }

        assertEquals(3, iteratedTasks.size());
        assertEquals(TaskStatus.TODO, iteratedTasks.get(0).getTaskStatus());
        assertEquals(TaskStatus.IN_PROGRESS, iteratedTasks.get(1).getTaskStatus());
        assertEquals(TaskStatus.DONE, iteratedTasks.get(2).getTaskStatus());
    }

    @Test
    void shouldProtectBoardInvariantsAndThrowExceptionsForInvalidActions() {
        assertThrows(IllegalArgumentException.class, () -> new Task("Name", null, LocalDate.now()));
        assertThrows(IllegalArgumentException.class, () -> new Task("Name", Priority.LOW, null));

        Task duplicateTask = new Task("Duplicate", Priority.MEDIUM, LocalDate.now());
        board.addTask(duplicateTask);

        IllegalArgumentException duplicateEx = assertThrows(IllegalArgumentException.class,
                () -> board.addTask(duplicateTask));
        assertEquals("Task is already on the board", duplicateEx.getMessage());

        Task phantomTask = new Task("Phantom", Priority.HIGH, LocalDate.now());
        IllegalArgumentException phantomEx = assertThrows(IllegalArgumentException.class,
                () -> board.moveTask(phantomTask, TaskStatus.DONE));
        assertEquals("Task not found in its expected column on the board", phantomEx.getMessage());
    }

}
