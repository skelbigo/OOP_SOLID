package org.example;

import java.util.Comparator;

public class PriceDescendingStrategy implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p2.getPrice(), p1.getPrice());
    }
}
