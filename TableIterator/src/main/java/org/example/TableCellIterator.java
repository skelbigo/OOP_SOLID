package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableCellIterator implements Iterator<String> {
    private final String[] columns;
    private final int[] rows;
    private int rowIndex = 0;
    private int colIndex = 0;

    public TableCellIterator(String[] columns, int[] rows) {
        if (columns == null || rows == null) {
            throw new IllegalArgumentException("Arrays can not be null");
        }
        for (String col : columns) {
            if (col == null) {
                throw new IllegalArgumentException("The column name cannot be null");
            }
        }
        this.columns = columns.clone();
        this.rows = rows.clone();
    }

    @Override
    public boolean hasNext() {
        if (columns.length == 0 || rows.length == 0) {
            return false;
        }
        return colIndex < columns.length;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("There are no more items");
        }
        String value = columns[colIndex] + rows[rowIndex];
        rowIndex++;
        if (rowIndex == rows.length) {
            rowIndex = 0;
            colIndex++;
        }
        return value;
    }
}
