package org.example;

import java.util.Objects;

public class Product {
    private final String name;
    private final double price;
    private final double rating;
    private final int salesCount;

    public Product(String name, double price, double rating, int salesCount) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The title cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("The price can not be negative");
        }
        if (rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("The rating should be between 0 and 5");
        }
        if (salesCount < 0) {
            throw new IllegalArgumentException("Sales volume cannot be negative");
        }
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.salesCount = salesCount;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Double.compare(rating, product.rating) == 0 && salesCount == product.salesCount && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, rating, salesCount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", salesCount=" + salesCount +
                '}';
    }
}
