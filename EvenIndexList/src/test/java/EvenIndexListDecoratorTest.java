import org.example.EvenIndexListDecorator;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvenIndexListDecoratorTest {
    @Test
    void shouldReturnOnlyElementsAtEvenIndices() {
        List<String> evenList = new EvenIndexListDecorator<>(Arrays.asList("a", "b", "c", "d", "e"));
        assertEquals(3, evenList.size());
        assertEquals("a", evenList.get(0));
        assertEquals("c", evenList.get(1));
        assertEquals("e", evenList.get(2));
    }

    @Test
    void shouldHandleEvenSizedOriginalList() {
        List<String> evenList = new EvenIndexListDecorator<>(Arrays.asList("a", "b", "c", "d"));
        assertEquals(2, evenList.size());
        assertEquals("a", evenList.get(0));
        assertEquals("c", evenList.get(1));
    }

    @Test
    void shouldReflectLiveChangesFromOriginalList() {
        List<String> original = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<String> evenList = new EvenIndexListDecorator<>(original);
        assertEquals(2, evenList.size());
        original.add("d");
        original.add("e");
        assertEquals(3, evenList.size());
        assertEquals("e", evenList.get(2));
    }

    @Test
    void shouldSupportForEachIteration() {
        List<String> evenList = new EvenIndexListDecorator<>(Arrays.asList("a", "b", "c", "d", "e"));
        StringBuilder result = new StringBuilder();
        for(String s : evenList) {
            result.append(s);
        }
        assertEquals("ace", result.toString());
    }

    @Test
    void shouldThrowExceptionForInvalidIndices() {
        List<String> evenList = new EvenIndexListDecorator<>(Arrays.asList("a", "b", "c"));
        assertThrows(IndexOutOfBoundsException.class, () -> evenList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> evenList.get(4));
    }

    @Test
    void shouldThrowExceptionWhenOriginalListIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new EvenIndexListDecorator<>(null),
                "The constructor should throw an IllegalArgumentException for null");
    }

    @Test
    void shouldHandleEmptyList() {
        List<String> evenList = new EvenIndexListDecorator<>(Collections.emptyList());

        assertEquals(0, evenList.size(), "The size of an empty list must be 0");
        assertThrows(IndexOutOfBoundsException.class, () -> evenList.get(0),
                "Accessing an empty list should throw an IndexOutOfBoundsException");
    }

    @Test
    void shouldBeReadOnly() {
        List<String> evenList = new EvenIndexListDecorator<>(Arrays.asList("a", "b", "c"));
        assertThrows(UnsupportedOperationException.class, () -> evenList.add("x"));
        assertThrows(UnsupportedOperationException.class, () -> evenList.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> evenList.set(0, "x"));
    }
}
