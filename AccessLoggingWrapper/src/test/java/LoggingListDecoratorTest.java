import org.example.LoggingListDecorator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoggingListDecoratorTest {
    @Test
    void shouldLogGetMethodCallWithCorrectIndex() {
        List<String> logs = new ArrayList<>();
        List<String> loggingList = new LoggingListDecorator<>(Arrays.asList("apple", "banana", "cherry"), logs::add);
        assertEquals("banana", loggingList.get(1));
        assertEquals(1, logs.size());
        assertEquals("get(1) was called", logs.get(0));
    }

    @Test
    void shouldLogSizeMethodCall() {
        List<String> logs = new ArrayList<>();
        List<String> loggingList = new LoggingListDecorator<>(Arrays.asList("apple", "banana"), logs::add);
        assertEquals(2, loggingList.size());
        assertEquals(1, logs.size());
        assertEquals("size() was called", logs.get(0));
    }

    @Test
    void shouldLogGetMethodEvenIfIndexIsOutOfBounds() {
        List<String> logs = new ArrayList<>();
        LoggingListDecorator<String> loggingList = new LoggingListDecorator<>(Arrays.asList("apple"), logs::add);
        assertThrows(IndexOutOfBoundsException.class, () -> loggingList.get(5));
        assertEquals(1, logs.size());
        assertEquals("get(5) was called", logs.get(0));
    }

    @Test
    void shouldNotLogSizeWhenCallingGet() {
        List<String> logs = new ArrayList<>();
        List<String> loggingList = new LoggingListDecorator<>(Arrays.asList("apple", "banana"), logs::add);
        loggingList.get(0);
        assertEquals(1, logs.size());
        assertEquals("get(0) was called", logs.get(0));
    }

    @Test
    void shouldThrowExceptionWhenArgumentsAreNull() {
        List<String> validList = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> new LoggingListDecorator<>(null));
        assertThrows(IllegalArgumentException.class, () -> new LoggingListDecorator<>(validList, null));
    }

    @Test
    void shouldThrowExceptionWithDetailedMessageWhenIndexIsNegative() {
        List<String> logs = new ArrayList<>();
        LoggingListDecorator<String> loggingList = new LoggingListDecorator<>(Arrays.asList("apple", "banana"), logs::add);

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> loggingList.get(-1));
        assertEquals("Index: -1, Size: 2", exception.getMessage());
        assertEquals(1, logs.size());
        assertEquals("get(-1) was called", logs.get(0));
    }

    @Test
    void shouldThrowExceptionWithDetailedMessageWhenIndexIsOutOfBounds() {
        List<String> logs = new ArrayList<>();
        LoggingListDecorator<String> loggingList = new LoggingListDecorator<>(Arrays.asList("apple", "banana"), logs::add);

        IndexOutOfBoundsException exceptionEqual = assertThrows(IndexOutOfBoundsException.class, () -> loggingList.get(2));
        assertEquals("Index: 2, Size: 2", exceptionEqual.getMessage());

        IndexOutOfBoundsException exceptionGreater = assertThrows(IndexOutOfBoundsException.class, () -> loggingList.get(5));
        assertEquals("Index: 5, Size: 2", exceptionGreater.getMessage());

        assertEquals(2, logs.size());
        assertEquals("get(2) was called", logs.get(0));
        assertEquals("get(5) was called", logs.get(1));
    }
}
