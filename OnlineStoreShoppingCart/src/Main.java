public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Product milk1 = new Product("Milk", 50);
        Product milk2 = new Product("Milk", 50);
        Product bread = new Product("Bread", 30);

        cart.addProduct(milk1);
        cart.addProduct(milk2);
        cart.addProduct(bread);

        System.out.println("Кількість товарів: " + cart.getNumberOfItems());
        System.out.println("Сума: " + cart.getSum());

        cart.removeProduct(new Product("Milk", 50));

        System.out.println("Кількість товарів: " + cart.getNumberOfItems());
        System.out.println("Сума: " + cart.getSum());

        for (Product p : cart.getProducts()) {
            System.out.println(p.getName() + " " + p.getPrice());
        }
    }
}