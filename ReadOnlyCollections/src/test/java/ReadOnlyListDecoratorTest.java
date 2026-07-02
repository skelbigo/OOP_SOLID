import org.example.ReadOnlyListDecorator;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReadOnlyListDecoratorTest {
    @Test
    void shouldAllowReadingElements() {
        List<String> readOnlyList = new ReadOnlyListDecorator<>(Arrays.asList("milk", "bread", "eggs"));

        assertEquals(3, readOnlyList.size());
        assertEquals("milk", readOnlyList.get(0));
        assertEquals("bread", readOnlyList.get(1));
        assertEquals("eggs", readOnlyList.get(2));
    }

    @Test
    void shouldSupportIteration() {
        List<String> readOnlyList = new ReadOnlyListDecorator<>(Arrays.asList("milk", "bread", "eggs"));

        StringBuilder result = new StringBuilder();
        for(String s : readOnlyList) {
            result.append(s).append(", ");
        }

        assertEquals("milk, bread, eggs, ", result.toString());
    }

    @Test
    void shouldReflectLiveChangesFromOriginalList() {
        List<String> original = new ArrayList<>(Arrays.asList("milk", "bread"));
        List<String> readOnlyList = new ReadOnlyListDecorator<>(original);

        assertEquals(2, readOnlyList.size());
        original.add("eggs");

        assertEquals(3, readOnlyList.size());
        assertEquals("eggs", readOnlyList.get(2));
    }

    @Test
    void shouldBlockDirectModifications() {
        List<String> readOnlyList = new ReadOnlyListDecorator<>(Arrays.asList("milk", "bread"));

        assertThrows(UnsupportedOperationException.class, () -> readOnlyList.add("eggs"));
        assertThrows(UnsupportedOperationException.class, () -> readOnlyList.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> readOnlyList.remove("milk"));
        assertThrows(UnsupportedOperationException.class, () -> readOnlyList.set(0, "eggs"));
    }

    @Test
    void shouldBlockModificationThroughIterator() {
        List<String> readOnlyList = new ReadOnlyListDecorator<>(Arrays.asList("milk", "bread"));
        Iterator<String> iterator = readOnlyList.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();

        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    void shouldThrowExceptionWhenOriginalListIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new ReadOnlyListDecorator<>(null));
    }

    @Test
    void shouldThrowExceptionWithCustomMessageWhenIndexIsNegative() {
        List<String> readOnlyList = new ReadOnlyListDecorator<>(Arrays.asList("milk", "bread"));
        assertThrows(IndexOutOfBoundsException.class, () -> readOnlyList.get(-1));
    }

    @Test
    void shouldThrowExceptionWithCustomMessageWhenIndexIsEqualToOrGreaterThanSize() {
        List<String> readOnlyList = new ReadOnlyListDecorator<>(Arrays.asList("milk", "bread"));
        assertThrows(IndexOutOfBoundsException.class, () -> readOnlyList.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> readOnlyList.get(5));
    }
}
