import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private static final double DELTA = 0.0001;

    @Test
    void shouldCalculateFinalPriceForUkraineWithoutDiscount() {
        Product product = new Product("Phone", 100.0, 0.0, new UkraineTaxStrategy());
        assertEquals(120.0, product.getFinalPrice(), DELTA);
    }

    @Test
    void shouldCalculateFinalPriceForPolandWithDiscount() {
        Product product = new Product("Phone", 100.0, 10.0, new PolandTaxStrategy());
        assertEquals(110.7, product.getFinalPrice(), DELTA);
    }

    @Test
    void shouldCalculateFinalPriceForGermany() {
        Product product = new Product("Laptop", 200.0, 50.0, new GermanyTaxStrategy());
        assertEquals(178.5, product.getFinalPrice(), DELTA);
    }

    @Test
    void shouldCalculateFinalPriceWithNoTax() {
        Product product = new Product("Gift Card", 150.0, 20.0, new NoTaxStrategy());
        assertEquals(130.0, product.getFinalPrice(), DELTA);
    }

    @Test
    void shouldAllowChangingTaxStrategyAtRuntime() {
        Product product = new Product("Tablet", 100.0, 0.0, new UkraineTaxStrategy());
        assertEquals(120.0, product.getFinalPrice(), DELTA);

        product.setTaxStrategy(new PolandTaxStrategy());
        assertEquals(123.0, product.getFinalPrice(), DELTA);
    }

    @Test
    void discountShouldNotExceedBasePrice() {
        Product product = new Product("Pen", 50.0, 100.0, new UkraineTaxStrategy());
        assertEquals(0.0, product.getFinalPrice(), DELTA);

        product.setDiscountAmount(200.0);
        assertEquals(0.0, product.getFinalPrice(), DELTA);
    }

    @Test
    void shouldThrowExceptionForInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Product("", 100.0, 0.0, new UkraineTaxStrategy()));
        assertThrows(IllegalArgumentException.class, () -> new Product("A", -10.0, 0.0, new UkraineTaxStrategy()));
        assertThrows(IllegalArgumentException.class, () -> new Product("A", 100.0, -5.0, new UkraineTaxStrategy()));
        assertThrows(IllegalArgumentException.class, () -> new Product("A", 100.0, 0.0, null));
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Product(null, 100.0, 0.0, new UkraineTaxStrategy())
        );
        assertEquals("The title cannot be empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenNameIsBlank() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("   ", 100.0, 0.0, new UkraineTaxStrategy())
        );
        assertEquals("The title cannot be empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenTaxStrategyIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("Phone", 100.0, 0.0, null)
        );
        assertEquals("A tax strategy cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenDiscountIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("Phone", 100.0, -15.0, new UkraineTaxStrategy())
        );
        assertEquals("A discount cannot be negative", exception.getMessage());

        Product validProduct = new Product("Phone", 100.0, 0.0, new UkraineTaxStrategy());
        IllegalArgumentException setterException = assertThrows(
                IllegalArgumentException.class,
                () -> validProduct.setDiscountAmount(-5.0)
        );
        assertEquals("A discount cannot be negative", setterException.getMessage());
    }


    @Test
    void shouldReturnCorrectNameAndBasePrice() {
        Product product = new Product("Smartphone", 999.99, 50.0, new UkraineTaxStrategy());
        assertEquals("Smartphone", product.getName());
        assertEquals(999.99, product.getBasePrice(), 0.0001);
    }
}

