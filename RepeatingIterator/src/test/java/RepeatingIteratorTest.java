import org.example.RepeatingIterator;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class RepeatingIteratorTest {

    @Test
    void shouldRepeatEachElementNTimes() {
        RepeatingIterator repeatingIterator = new RepeatingIterator(new int[] {7, 8}, 3);

        assertTrue(repeatingIterator.hasNext());
        assertEquals(7, repeatingIterator.next());
        assertEquals(7, repeatingIterator.next());
        assertEquals(7, repeatingIterator.next());

        assertTrue(repeatingIterator.hasNext());
        assertEquals(8, repeatingIterator.next());
        assertEquals(8, repeatingIterator.next());
        assertEquals(8, repeatingIterator.next());

        assertFalse(repeatingIterator.hasNext());
    }

    @Test
    void shouldHandleSingleElementAndSingleRepetition() {
        RepeatingIterator repeatingIterator = new RepeatingIterator(new int[] {42}, 1);

        assertTrue(repeatingIterator.hasNext());
        assertEquals(42, repeatingIterator.next());
        assertFalse(repeatingIterator.hasNext());
    }

    @Test
    void shouldThrowExceptionWhenNIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new RepeatingIterator(new int[] {42}, 0),
                "There must be an error if n = 0");
        assertThrows(IllegalArgumentException.class, () -> new RepeatingIterator(new int[] {42}, -2),
                "There must be an error if n is negative");
    }

    @Test
    void shouldThrowExceptionWhenCallingNextWithNoElements() {
        RepeatingIterator repeatingIterator = new RepeatingIterator(new int[] {}, 2);
        assertThrows(NoSuchElementException.class, repeatingIterator::next);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new RepeatingIterator(null, 3),
                "There must be an error if the array is null");
    }

    @Test
    void shouldNotAdvanceIteratorWhenCallingHasNextMultipleTimes() {
        RepeatingIterator repeatingIterator = new RepeatingIterator(new int[] {10, 20}, 2);
        assertTrue(repeatingIterator.hasNext());
        assertTrue(repeatingIterator.hasNext());
        assertTrue(repeatingIterator.hasNext());

        assertEquals(10, repeatingIterator.next(), "The iterator must still return the first element");
    }

    @Test
    void shouldThrowExceptionWhenCallingNextAfterExhaustingArray() {
        RepeatingIterator repeatingIterator = new RepeatingIterator(new int[] {42}, 1);

        assertTrue(repeatingIterator.hasNext());
        assertEquals(42, repeatingIterator.next());
        assertFalse(repeatingIterator.hasNext());

        assertThrows(NoSuchElementException.class, repeatingIterator::next,
                "There should be an error when calling next() after the end of the array");
    }

    @Test
    void shouldReturnFalseForHasNextOnEmptyArray() {
        RepeatingIterator repeatingIterator = new RepeatingIterator(new int[] {}, 2);
        assertFalse(repeatingIterator.hasNext(), "For an empty array, hasNext should immediately return false");
    }

    @Test
    void shouldThrowExceptionWhenCallingNextOnEmptyArray() {
        int[] arr = {};
        RepeatingIterator iterator = new RepeatingIterator(arr, 2);

        assertThrows(NoSuchElementException.class, iterator::next,
                "Calling next() on an empty array should throw a NoSuchElementException");
    }
}
