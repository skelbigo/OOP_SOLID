package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DuplicateIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int currentIndex = 0;
    private boolean returnedOnce = false;

    public DuplicateIterator(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < numbers.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("There are no more items");
        }
        int value = numbers[currentIndex];
        if (!returnedOnce) {
            returnedOnce = true;
        } else {
            returnedOnce = false;
            currentIndex++;
        }
        return value;
    }
}
