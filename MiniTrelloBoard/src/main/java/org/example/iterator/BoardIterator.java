package org.example.iterator;

import org.example.models.Board;
import org.example.models.Task;
import org.example.models.TaskStatus;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BoardIterator implements Iterator<Task> {
    private final Iterator<List<Task>> columnIterators;
    private Iterator<Task> currentColumnIterator;

    public BoardIterator(Board board) {
        this.columnIterators = List.of(
                board.getColumn(TaskStatus.TODO),
                board.getColumn(TaskStatus.IN_PROGRESS),
                board.getColumn(TaskStatus.DONE)
        ).iterator();
        this.currentColumnIterator = java.util.Collections.emptyIterator();
    }

    @Override
    public boolean hasNext() {
        while (!currentColumnIterator.hasNext() && columnIterators.hasNext()) {
            currentColumnIterator = columnIterators.next().iterator();
        }
        return currentColumnIterator.hasNext();
    }

    @Override
    public Task next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentColumnIterator.next();
    }
}
