package org.example;

public class NoTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(double amountBeforeTax) {
        return 0.0;
    }
}
