import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("The product can not be null");
        }
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("The product can not be null");
        }
        if (!products.contains(product)) {
            throw new IllegalArgumentException("This product not consist in the shopping cart");
        }
        products.remove(product);
    }

    public double getSum() {
        double sum = 0;
        for(Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public int getNumberOfItems() {
        return products.size();
    }

    public void clear() {
        products.clear();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public int getProductCount(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("The product can not be null");
        }

        return Collections.frequency(products, product);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
