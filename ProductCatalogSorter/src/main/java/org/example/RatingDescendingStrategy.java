package org.example;

import java.util.Comparator;

public class RatingDescendingStrategy implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p2.getRating(), p1.getRating());
    }
}
