package org.example;

import java.util.AbstractList;
import java.util.List;
import java.util.function.Consumer;

public class LoggingListDecorator<T> extends AbstractList<T> {
    private final List<T> originalList;
    private final Consumer<String> logger;

    public LoggingListDecorator(List<T> originalList, Consumer<String> logger) {
        if (originalList == null) {
            throw new IllegalArgumentException("The original list cannot be null");
        }
        if (logger == null) {
            throw new IllegalArgumentException("Logger cannot be null");
        }
        this.originalList = originalList;
        this.logger = logger;
    }

   public LoggingListDecorator(List<T> originalList) {
        this(originalList, System.out::println);
   }

    @Override
    public T get(int index) {
        logger.accept("get(" + index + ") was called");
        if (index < 0 || index >= originalList.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + originalList.size());
        }
        return originalList.get(index);
    }

    @Override
    public int size() {
        logger.accept("size() was called");
        return originalList.size();
    }
}
