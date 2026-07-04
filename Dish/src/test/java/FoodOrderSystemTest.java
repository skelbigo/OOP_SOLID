import org.example.Order;
import org.example.decorators.*;
import org.example.factory.*;
import org.example.models.Dish;
import org.example.strategies.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class FoodOrderSystemTest {
    private DishFactory factory;

    @BeforeEach
    void setUp() {
        factory = new DishFactory();
    }

    @Test
    void shouldCreateComplexBurgerOrderSuccessfully() {
        Dish myBurger = factory.createDish(DishType.BURGER);

        myBurger = new ExtraCheese(myBurger);
        myBurger = new DoubleMeat(myBurger);
        myBurger = new NoOnion(myBurger);

        DeliveryStrategy courier = new CourierDelivery();

        Order order = new Order(myBurger, courier);

        assertEquals("Classic Beef Burger + Extra Cheese + Double Meat (No Onion)", myBurger.getDescription());
        assertEquals(0, new BigDecimal("250.0").compareTo(myBurger.getPrice()));
        assertEquals(0, new BigDecimal("310.0").compareTo(order.getTotalPrice()));
    }

    @Test
    void shouldCreatePizzaWithExpressDelivery() {
        Dish pizza = factory.createDish(DishType.PIZZA);

        pizza = new SpicySauce(pizza);

        DeliveryStrategy express = new ExpressDelivery();
        Order order = new Order(pizza, express);
        assertEquals("Margherita Pizza + Spicy Sauce", pizza.getDescription());
        assertEquals(0, new BigDecimal("265.0").compareTo(pizza.getPrice()));
        assertEquals(0, new BigDecimal("391.50").compareTo(order.getTotalPrice()));
    }

    @Test
    void shouldCreateSaladWithPickup() {
        Dish salad = factory.createDish(DishType.SALAD);
        DeliveryStrategy pickup = new PickupDelivery();

        Order order = new Order(salad, pickup);
        assertEquals(new BigDecimal("120.0"), salad.getPrice());
        assertTrue(order.generateReceipt().contains("Pickup"));
    }

    @Test
    void decoratorsShouldThrowExceptionForNullDish() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraCheese(null));
    }

    @Test
    void orderShouldThrowExceptionForNullInputs() {
        Dish dish = factory.createDish(DishType.BURGER);
        DeliveryStrategy delivery = new CourierDelivery();

        assertThrows(IllegalArgumentException.class, () -> new Order(null, delivery));
        assertThrows(IllegalArgumentException.class, () -> new Order(dish, null));
    }
}
