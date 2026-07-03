import org.example.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductSorterTest {
    private List<Product> products;
    private Product laptop;
    private Product mouse;
    private Product keyboard;
    private Product monitor;

    @BeforeEach
    void setup() {
        laptop = new Product("Laptop", 1500.0, 4.8, 150);
        mouse = new Product("Mouse", 25.0, 4.2, 800);
        keyboard = new Product("Keyboard", 75.0, 4.5, 450);
        monitor = new Product("Monitor", 300.0, 4.9, 120);
        products = Arrays.asList(laptop, mouse, keyboard, monitor);
    }

    @Test
    void shouldSortByPriceAscending() {
        ProductSorter sorter = new ProductSorter(new PriceAscendingStrategy());
        List<Product> sorted = sorter.sort(products);
        assertEquals(Arrays.asList(mouse, keyboard, monitor, laptop), sorted);
    }

    @Test
    void shouldSortByPriceDescending() {
        ProductSorter sorter = new ProductSorter(new PriceDescendingStrategy());
        List<Product> sorted = sorter.sort(products);
        assertEquals(Arrays.asList(laptop, monitor, keyboard, mouse), sorted);
    }

    @Test
    void shouldSortByRatingDescending() {
        ProductSorter sorter = new ProductSorter(new RatingDescendingStrategy());
        List<Product> sorted = sorter.sort(products);
        assertEquals(Arrays.asList(monitor, laptop, keyboard, mouse), sorted);
    }

    @Test
    void shouldSortByPopularityDescending() {
        ProductSorter sorter = new ProductSorter(new PopularityDescendingStrategy());
        List<Product> sorted = sorter.sort(products);
        assertEquals(Arrays.asList(mouse, keyboard, laptop, monitor), sorted);
    }

    @Test
    void shouldSortByNameAscending() {
        ProductSorter sorter = new ProductSorter(new NameAscendingStrategy());
        List<Product> sorted = sorter.sort(products);
        assertEquals(Arrays.asList(keyboard, laptop, monitor, mouse), sorted);
    }

    @Test
    void shouldAllowChangingStrategyAtRuntime() {
        ProductSorter sorter = new ProductSorter(new PriceAscendingStrategy());
        assertEquals(mouse, sorter.sort(products).get(0));

        sorter.setSortStrategy(new RatingDescendingStrategy());
        assertEquals(monitor, sorter.sort(products).get(0));
    }

    @Test
    void shouldNotMutateOriginalList() {
        ProductSorter sorter = new ProductSorter(new NameAscendingStrategy());
        sorter.sort(products);
        assertEquals(laptop, products.get(0));
        assertEquals(mouse, products.get(1));
    }

    @Test
    void shouldThrowExceptionsForInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new ProductSorter(null));
        ProductSorter sorter = new ProductSorter(new PriceAscendingStrategy());
        assertThrows(IllegalArgumentException.class, () -> sorter.setSortStrategy(null));
        assertThrows(IllegalArgumentException.class, () -> sorter.sort(null));
    }

    @Test
    void shouldCreateProductWhenValidArgumentsProvided() {
        Product product = new Product("Laptop", 1500.0, 4.5, 100);
        assertNotNull(product);
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Product(null, 1500.0, 4.5, 100));
        assertEquals("The title cannot be empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenPriceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Product("Laptop", -1.0, 4.5, 100));
        assertEquals("The price can not be negative", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenRatingIsLessThanZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Product("Laptop", 1500.0, -0.1, 100));
        assertEquals("The rating should be between 0 and 5", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenRatingIsGreaterThanFive() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Product("Laptop", 1500.0, 5.1, 100));
        assertEquals("The rating should be between 0 and 5", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithExactMessageWhenSalesCountIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Product("Laptop", 1500.0, 4.5, -5));
        assertEquals("Sales volume cannot be negative", exception.getMessage());
    }

    @Test
    void equalsShouldReturnTrueForSameInstance() {
        Product product = new Product("Laptop", 1500.0, 4.5, 100);
        assertTrue(product.equals(product), "An object must be equal to itself");
    }

    @Test
    void equalsShouldReturnTrueForDifferentInstancesWithSameData() {
        Product product1 = new Product("Laptop", 1500.0, 4.5, 100);
        Product product2 = new Product("Laptop", 1500.0, 4.5, 100);

        assertTrue(product1.equals(product2));
        assertTrue(product2.equals(product1));
    }

    @Test
    void equalsShouldReturnFalseForNullOrDifferentClass() {
        Product product = new Product("Laptop", 1500.0, 4.5, 100);
        assertFalse(product.equals(null), "equals(null) should return false");
        assertFalse(product.equals("Some String"), "The equals method with another class should return false");
    }

    @Test
    void equalsShouldReturnFalseIfFieldsAreDifferent() {
        Product base = new Product("Laptop", 1500.0, 4.5, 100);
        Product diffName = new Product("Phone", 1500.0, 4.5, 100);
        Product diffPrice = new Product("Laptop", 1200.0, 4.5, 100);
        Product diffRating = new Product("Laptop", 1500.0, 4.0, 100);
        Product diffSales = new Product("Laptop", 1500.0, 4.5, 50);

        assertFalse(base.equals(diffName));
        assertFalse(base.equals(diffPrice));
        assertFalse(base.equals(diffRating));
        assertFalse(base.equals(diffSales));
    }

    @Test
    void hashCodeShouldBeSameForEqualObjects() {
        Product product1 = new Product("Laptop", 1500.0, 4.5, 100);
        Product product2 = new Product("Laptop", 1500.0, 4.5, 100);

        assertEquals(product1.hashCode(), product2.hashCode(), "Identical objects must have the same hashCode");
    }

    @Test
    void toStringShouldReturnCorrectlyFormattedString() {
        Product product = new Product("Laptop", 1500.0, 4.5, 100);
        String expected = "Product{name='Laptop', price=1500.0, rating=4.5, salesCount=100}";
        assertEquals(expected, product.toString());
    }
}
