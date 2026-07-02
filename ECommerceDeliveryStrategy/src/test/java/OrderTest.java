import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private static final double DELTA = 0.0001;

    @Test
    void shouldCalculateCourierDeliveryCost() {
        Order order = new Order(2.0, 5.0, new CourierDeliveryStrategy());
        assertEquals(100.0, order.getDeliveryCost(), DELTA);
    }

    @Test
    void shouldCalculateExpressDeliveryCost() {
        Order order = new Order(2.0, 5.0, new ExpressDeliveryStrategy());
        assertEquals(200.0, order.getDeliveryCost(), DELTA);
    }

    @Test
    void shouldCalculatePostalDeliveryCostBasedOnWeight() {
        Order order = new Order(3.0, 100.0, new PostalDeliveryStrategy());
        assertEquals(45.0, order.getDeliveryCost(), DELTA);
    }

    @Test
    void shouldReturnZeroForPickupDelivery() {
        Order order = new Order(10.0, 50.0, new PickupDeliveryStrategy());
        assertEquals(0.0, order.getDeliveryCost(), DELTA);
    }

    @Test
    void shouldHandleZeroDistanceForCourierAndExpress() {
        Order courierOrder = new Order(2.0, 0.0, new CourierDeliveryStrategy());
        assertEquals(50.0, courierOrder.getDeliveryCost(), DELTA, "Only the base price should be charged");

        Order expressOrder = new Order(2.0, 0.0, new ExpressDeliveryStrategy());
        assertEquals(100.0, expressOrder.getDeliveryCost(), DELTA, "Only the base express price should be charged");
    }

    @Test
    void expressMustBeMoreExpensiveThanCourierForSameOrder() {
        Order courierOrder = new Order(5.0, 15.0, new CourierDeliveryStrategy());
        Order expressOrder = new Order(5.0, 15.0, new ExpressDeliveryStrategy());

        assertTrue(expressOrder.getDeliveryCost() > courierOrder.getDeliveryCost(),
                "Express delivery should always be more expensive than standard courier service for the same order");
    }

    @Test
    void shouldAllowChangingStrategyAtRuntime() {
        Order order = new Order(4.0, 10.0, new PostalDeliveryStrategy());
        assertEquals(60.0, order.getDeliveryCost(), DELTA);

        order.setDeliveryStrategy(new PickupDeliveryStrategy());
        assertEquals(0.0, order.getDeliveryCost(), DELTA);

        order.setDeliveryStrategy(new ExpressDeliveryStrategy());
        assertEquals(300.0, order.getDeliveryCost(), DELTA);
    }

    @Test
    void shouldThrowExceptionForInvalidOrderParameters() {
        assertThrows(IllegalArgumentException.class, () -> new Order(-1.0, 5.0, new PickupDeliveryStrategy()));
        assertThrows(IllegalArgumentException.class, () -> new Order(1.0, -5.0, new PickupDeliveryStrategy()));
        assertThrows(IllegalArgumentException.class, () -> new Order(1.0, 5.0, null));
    }

    @Test
    void shouldThrowExceptionWhenSettingNullStrategy() {
        Order order = new Order(2.0, 5.0, new PickupDeliveryStrategy());
        assertThrows(IllegalArgumentException.class, () -> order.setDeliveryStrategy(null));
    }

    @Test
    void shouldThrowExceptionWhenCallingCalculateCostDirectlyWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new CourierDeliveryStrategy().calculateCost(null));
        assertThrows(IllegalArgumentException.class, () -> new ExpressDeliveryStrategy().calculateCost(null));
        assertThrows(IllegalArgumentException.class, () -> new PostalDeliveryStrategy().calculateCost(null));
        assertThrows(IllegalArgumentException.class, () -> new PickupDeliveryStrategy().calculateCost(null));
    }
}
