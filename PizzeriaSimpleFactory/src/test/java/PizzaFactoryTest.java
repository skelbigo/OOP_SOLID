import org.example.Pizzeria;
import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PizzaFactoryTest {

    private PizzaFactory factory;
    private Pizzeria pizzeria;

    @BeforeEach
    void setUp() {
        factory = new PizzaFactory();
        pizzeria = new Pizzeria(factory);
    }

    @Test
    void shouldCreateMargheritaPizza() {
        Pizza pizza = factory.createPizza(PizzaType.MARGHERITA);

        assertInstanceOf(MargheritaPizza.class, pizza);
        assertEquals("Margherita", pizza.getName());
        assertEquals(new BigDecimal("150.0"), pizza.getPrice());
    }

    @Test
    void pizzeriaShouldProcessOrderSuccessfully() {
        Pizza pizza = pizzeria.orderPizza(PizzaType.VEGAN);
        assertNotNull(pizza);
        assertInstanceOf(VeganPizza.class, pizza);
    }

    @Test
    void shouldThrowExceptionWhenTypeIsNullInFactory() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createPizza(null)
        );
        assertEquals("Pizza type cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTypeIsNullInPizzeria() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pizzeria.orderPizza(null)
        );
        assertEquals("Cannot order a null pizza type", exception.getMessage());
    }
}