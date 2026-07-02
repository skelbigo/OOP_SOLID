import org.example.TableCellIterator;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class TableCellIteratorTest {
    @Test
    void shouldIterateInColumnMajorOrder() {
        TableCellIterator tableCellIterator = new TableCellIterator(new String[] {"A", "B", "C"}, new int[] {1, 2});
        assertTrue(tableCellIterator.hasNext());
        assertEquals("A1", tableCellIterator.next());
        assertEquals("A2", tableCellIterator.next());

        assertTrue(tableCellIterator.hasNext());
        assertEquals("B1", tableCellIterator.next());
        assertEquals("B2", tableCellIterator.next());

        assertTrue(tableCellIterator.hasNext());
        assertEquals("C1", tableCellIterator.next());
        assertEquals("C2", tableCellIterator.next());

        assertFalse(tableCellIterator.hasNext());
    }

    @Test
    void shouldReturnFalseForEmptyColumnsArray() {
        TableCellIterator tableCellIterator = new TableCellIterator(new String[] {}, new int[] {1, 2});
        assertFalse(tableCellIterator.hasNext(), "If there are no columns, the iterator must be empty");
        assertThrows(NoSuchElementException.class, tableCellIterator::next);
    }

    @Test
    void shouldReturnFalseForEmptyRowsArray() {
        TableCellIterator tableCellIterator = new TableCellIterator(new String[] {"A", "B", "C"}, new int[] {});
        assertFalse(tableCellIterator.hasNext(), "If there are no rows, the iterator must be empty");
        assertThrows(NoSuchElementException.class, tableCellIterator::next);
    }

    @Test
    void shouldThrowExceptionWhenArraysAreNull() {
        assertThrows(IllegalArgumentException.class, () -> new TableCellIterator(null, new int[] {1, 2}));
        assertThrows(IllegalArgumentException.class, () -> new TableCellIterator(new String[] {"A", "B", "C"}, null));
    }

    @Test
    void shouldNotAdvanceIteratorWhenCallingHasNextMultipleTimes() {
        TableCellIterator tableCellIterator = new TableCellIterator(new String[] {"A", "B", "C"}, new int[] {1, 2});
        assertTrue(tableCellIterator.hasNext());
        assertTrue(tableCellIterator.hasNext());
        assertTrue(tableCellIterator.hasNext());

        assertEquals("A1", tableCellIterator.next(), "The iterator must still return the first element");
    }

    @Test
    void shouldProtectInternalStateFromExternalModifications() {
        String[] columns = {"A", "B"};
        int[] rows = {1, 2};
        TableCellIterator iterator = new TableCellIterator(columns, rows);
        columns[0] = "Z";
        rows[0] = 99;
        assertEquals("A1", iterator.next(), "An iterator must use its own immutable copy of the array");
    }

    @Test
    void shouldThrowExceptionWhenCallingNextAfterNormalExhaustion() {
        TableCellIterator iterator = new TableCellIterator(new String[]{"A"}, new int[]{1});
        assertEquals("A1", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void shouldHandleMinimal1x1Table() {
        TableCellIterator iterator = new TableCellIterator(new String[]{"A"}, new int[]{1});
        assertTrue(iterator.hasNext());
        assertEquals("A1", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldHandleMultipleColumnsAndSingleRow() {
        TableCellIterator iterator = new TableCellIterator(new String[]{"A", "B"}, new int[]{1});
        assertEquals("A1", iterator.next());
        assertEquals("B1", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldThrowExceptionIfColumnArrayContainsNullElement() {
        String[] cols = {"A", null, "C"};
        int[] rows = {1, 2};

        assertThrows(IllegalArgumentException.class, () -> new TableCellIterator(cols, rows),
                "Creating an iterator with a null element inside an array should throw an error");
    }
}
