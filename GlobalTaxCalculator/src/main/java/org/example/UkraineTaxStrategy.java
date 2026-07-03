package org.example;

public class UkraineTaxStrategy implements TaxStrategy {
    private static final double TAX_RATE = 0.20;

    @Override
    public double calculateTax(double amountBeforeTax) {
        return amountBeforeTax * TAX_RATE;
    }
}
