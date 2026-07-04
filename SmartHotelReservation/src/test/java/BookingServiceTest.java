import org.example.factory.RoomFactory;
import org.example.factory.RoomType;
import org.example.models.*;
import org.example.models.rooms.*;
import org.example.observer.*;
import org.example.service.BookingService;
import org.example.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingSystemTest {
    private BookingService bookingService;
    private RoomFactory factory;
    private BookingEventManager eventManager;
    private Client testClient;
    private final LocalDate futureDate = LocalDate.now().plusDays(5);

    @BeforeEach
    void setUp() {
        factory = new RoomFactory();
        eventManager = new BookingEventManager();
        eventManager.subscribe(new EmailNotifier());
        bookingService = new BookingService(factory, eventManager);
        testClient = new Client("Oleksandra", "sasha@mail.com");
    }

    @Test
    void roomFactoryShouldCreateAllTypesAndThrowOnNull() {
        assertTrue(factory.createRoom(RoomType.STANDARD) instanceof StandardRoom);
        assertTrue(factory.createRoom(RoomType.FAMILY) instanceof FamilyRoom);
        assertTrue(factory.createRoom(RoomType.LUX) instanceof LuxRoom);
        assertThrows(IllegalArgumentException.class, () -> factory.createRoom(null));
    }

    @Test
    void pricingStrategiesShouldCalculateCorrectly() {
        BigDecimal base = new BigDecimal("1000.00");
        int nights = 3;

        assertEquals(0, new BigDecimal("3000.00").compareTo(new RegularPricing().calculatePrice(base, nights)));
        assertEquals(0, new BigDecimal("2700.00").compareTo(new DiscountPricing().calculatePrice(base, nights)));
        assertEquals(0, new BigDecimal("2550.00").compareTo(new LoyalClientPricing().calculatePrice(base, nights)));
        assertEquals(0, new BigDecimal("3600.00").compareTo(new SeasonalPricing().calculatePrice(base, nights)));
    }

    @Test
    void pricingStrategiesShouldThrowOnInvalidInputs() {
        PricingStrategy strategy = new RegularPricing();
        assertThrows(IllegalArgumentException.class, () -> strategy.calculatePrice(null, 3));
        assertThrows(IllegalArgumentException.class, () -> strategy.calculatePrice(new BigDecimal("-100"), 3));
        assertThrows(IllegalArgumentException.class, () -> strategy.calculatePrice(new BigDecimal("1000"), 0));
    }

    @Test
    void bookingServiceShouldThrowOnNullInputs() {
        assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(null, RoomType.STANDARD, 1, futureDate, new RegularPricing()));
        assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(testClient, null, 1, futureDate, new RegularPricing()));
        assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(testClient, RoomType.STANDARD, 1, null, new RegularPricing()));
        assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(testClient, RoomType.STANDARD, 1, futureDate, null));
        assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(testClient, RoomType.STANDARD, -1, futureDate, new RegularPricing()));
    }

    @Test
    void eventManagerShouldValidateInputsAndIgnoreDuplicates() {
        BookingObserver observer = new EmailNotifier();
        eventManager.subscribe(observer);
        eventManager.subscribe(observer);

        assertThrows(IllegalArgumentException.class, () -> eventManager.subscribe(null));
        assertThrows(IllegalArgumentException.class, () -> eventManager.notifyCreated(null));
    }

    @Test
    void clientShouldValidateEmailAndMessage() {
        assertThrows(IllegalArgumentException.class, () -> new Client("Name", "not-an-email"));
        assertThrows(IllegalArgumentException.class, () -> testClient.receiveMessage(null));

        assertThrows(UnsupportedOperationException.class, () -> testClient.getInbox().add("Hack"));
    }

    @Test
    void bookingShouldRejectPastDates() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, () ->
                new Booking(testClient, new StandardRoom(), pastDate, 2, new BigDecimal("2000.00"))
        );
    }

    @Test
    void fullBookingLifecycleShouldSendCorrectNotifications() {
        Booking booking = bookingService.createBooking(testClient, RoomType.LUX, 2, futureDate, new RegularPricing());
        bookingService.remindAboutCheckIn(booking);
        bookingService.cancelBooking(booking);

        List<String> inbox = testClient.getInbox();
        assertEquals(3, inbox.size());
        assertTrue(inbox.get(0).contains("Your booking for Lux Room is confirmed. Total: 10000.0"));
        assertTrue(inbox.get(1).contains("check-in is tomorrow at " + futureDate));
        assertTrue(inbox.get(2).contains("Your booking has been cancelled"));
    }
}