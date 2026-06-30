import org.example.DuplicateIterator;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateIteratorTest {
    @Test
    void shouldRepeatEachElementTwice() {
        int[] arr = {1, 2, 3};
        DuplicateIterator duplicateIterator = new DuplicateIterator(arr);
        assertTrue(duplicateIterator.hasNext());
        assertEquals(1, duplicateIterator.next());
        assertTrue(duplicateIterator.hasNext());
        assertEquals(1, duplicateIterator.next());
        assertTrue(duplicateIterator.hasNext());
        assertEquals(2, duplicateIterator.next());
        assertTrue(duplicateIterator.hasNext());
        assertEquals(2, duplicateIterator.next());
        assertTrue(duplicateIterator.hasNext());
        assertEquals(3, duplicateIterator.next());
        assertTrue(duplicateIterator.hasNext());
        assertEquals(3, duplicateIterator.next());
        assertFalse(duplicateIterator.hasNext());
    }

    @Test
    void shouldHandleSingleElementArray() {
        int[] arr = {5};
        DuplicateIterator duplicateIterator = new DuplicateIterator(arr);
        assertTrue(duplicateIterator.hasNext());
        assertEquals(5, duplicateIterator.next());
        assertTrue(duplicateIterator.hasNext());
        assertEquals(5, duplicateIterator.next());
        assertFalse(duplicateIterator.hasNext());
    }

    @Test
    void shouldReturnFalseForEmptyArray() {
        int[] arr = {};
        DuplicateIterator duplicateIterator = new DuplicateIterator(arr);
        assertFalse(duplicateIterator.hasNext(), "For an empty array, hasNext must be false");
    }

    @Test
    void shouldThrowExceptionWhenCallingNextWithNoElements() {
        int[] arr = {};
        DuplicateIterator duplicateIterator = new DuplicateIterator(arr);
        assertThrows(NoSuchElementException.class, duplicateIterator::next, "Calling next() when no elements" +
                " are available should throw a NoSuchElementException");
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new DuplicateIterator(null), "Creating an " +
                "iterator from null should throw an IllegalArgumentException");
    }
}
