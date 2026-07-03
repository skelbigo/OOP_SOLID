package org.example;

public class Product {
    private final String name;
    private final double basePrice;
    private double discountAmount;
    private TaxStrategy taxStrategy;

    public Product(String name, double basePrice, double discountAmount, TaxStrategy taxStrategy) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("The title cannot be empty");
        if (basePrice < 0) throw new IllegalArgumentException("The base price cannot be negative");
        if (discountAmount < 0) throw new IllegalArgumentException("A discount cannot be negative");
        if (taxStrategy == null) throw new IllegalArgumentException("A tax strategy cannot be null");

        if (discountAmount > basePrice) {
            this.discountAmount = basePrice;
        } else {
            this.discountAmount = discountAmount;
        }

        this.name = name;
        this.basePrice = basePrice;
        this.taxStrategy = taxStrategy;
    }

    public void setTaxStrategy(TaxStrategy taxStrategy) {
        if (taxStrategy == null) throw new IllegalArgumentException("A tax strategy cannot be null");
        this.taxStrategy = taxStrategy;
    }

    public void setDiscountAmount(double discountAmount) {
        if (discountAmount < 0) throw new IllegalArgumentException("A discount cannot be negative");
        this.discountAmount = Math.min(discountAmount, this.basePrice);
    }

    public double getFinalPrice() {
        double priceAfterDiscount = basePrice - discountAmount;
        double taxAmount = taxStrategy.calculateTax(priceAfterDiscount);
        return  priceAfterDiscount + taxAmount;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
