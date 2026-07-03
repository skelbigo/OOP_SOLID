package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductSorter {
    private Comparator<Product> sortStrategy;

    public ProductSorter(Comparator<Product> sortStrategy) {
        if (sortStrategy == null) {
            throw new IllegalArgumentException("The sorting strategy cannot be null");
        }
        this.sortStrategy = sortStrategy;
    }

    public void setSortStrategy(Comparator<Product> sortStrategy) {
        if (sortStrategy == null) {
            throw new IllegalArgumentException("The sorting strategy cannot be null");
        }
        this.sortStrategy = sortStrategy;
    }

    public List<Product> sort(List<Product> products) {
        if (products == null) {
            throw new IllegalArgumentException("The list of items cannot be null");
        }
        List<Product> sortedList = new ArrayList<>(products);
        sortedList.sort(sortStrategy);
        return sortedList;
    }
}
