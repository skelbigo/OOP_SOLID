package org.example;

public class PolandTaxStrategy implements TaxStrategy {
    private static final double TAX_RATE = 0.23;
    @Override
    public double calculateTax(double amountBeforeTax) {
        return amountBeforeTax * TAX_RATE;
    }
}
