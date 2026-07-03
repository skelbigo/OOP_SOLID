package org.example;

import java.util.Comparator;

public class PopularityDescendingStrategy implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Integer.compare(p2.getSalesCount(), p1.getSalesCount());
    }
}
