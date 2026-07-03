package org.example;

public class GermanyTaxStrategy implements TaxStrategy {
    private static final double TAX_RATE = 0.19;

    @Override
    public double calculateTax(double amountBeforeTax) {
        return amountBeforeTax * TAX_RATE;
    }
}