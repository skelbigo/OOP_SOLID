import java.util.Objects;

public class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name of product can not be blank or null");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("The price of product can be positive number");
        }
        this.name = name.trim();
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
