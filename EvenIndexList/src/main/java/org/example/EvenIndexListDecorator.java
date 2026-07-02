package org.example;

import java.util.AbstractList;
import java.util.List;

public class EvenIndexListDecorator<T> extends AbstractList<T> {
    private final List<T> originalList;

    public EvenIndexListDecorator(List<T> originalList) {
        if (originalList == null) {
            throw new IllegalArgumentException("The original list cannot be null");
        }
        this.originalList = originalList;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index outside the range: " + index);
        }

        return originalList.get(index * 2);
    }

    @Override
    public int size() {
        return (originalList.size() + 1) / 2;
    }
}
