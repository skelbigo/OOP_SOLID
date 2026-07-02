package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RepeatingIterator implements Iterator<Integer> {
    private final int[] numbers;
    private final int n;
    private int repetitionsLeft;
    private int currentIndex = 0;

    public RepeatingIterator(int[] numbers, int n) {
        if (numbers == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("The number of repetitions must be greater than 0");
        }
        this.numbers = numbers;
        this.n = n;
        this.repetitionsLeft = n;
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
        repetitionsLeft--;
        if (repetitionsLeft == 0) {
            currentIndex++;
            repetitionsLeft = n;
        }
        return value;
    }
}
