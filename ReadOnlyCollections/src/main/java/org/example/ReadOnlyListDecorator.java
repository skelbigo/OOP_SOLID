package org.example;

import java.util.AbstractList;
import java.util.List;

public class ReadOnlyListDecorator<T> extends AbstractList<T> {
    private final List<T> originalList;

    public ReadOnlyListDecorator(List<T> originalList) {
        if (originalList == null) {
            throw new IllegalArgumentException("The original list cannot be null");
        }
        this.originalList = originalList;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        return originalList.get(index);
    }

    @Override
    public int size() {
        return originalList.size();
    }
}
