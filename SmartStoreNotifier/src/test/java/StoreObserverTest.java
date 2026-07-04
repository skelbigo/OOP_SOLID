import org.example.EmailSubscriber;
import org.example.SmsSubscriber;
import org.example.Store;
import org.example.TelegramSubscriber;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StoreObserverTest {

    private Store store;
    private EmailSubscriber emailUser;
    private SmsSubscriber smsUser;
    private TelegramSubscriber tgUser;

    @BeforeEach
    void setUp() {
        store = new Store();
        emailUser = new EmailSubscriber("test@mail.com");
        smsUser = new SmsSubscriber("+380991234567");
        tgUser = new TelegramSubscriber("@sasha");
    }

    @Test
    void shouldFormatMessagesCorrectlyForAllTypes() {
        store.subscribe("tech", emailUser);
        store.subscribe("tech", smsUser);
        store.subscribe("tech", tgUser);

        store.announceDiscount("tech", "50% OFF!");

        assertEquals("Email to [test@mail.com] | Category: tech | 50% OFF!", emailUser.getNotifications().get(0));
        assertEquals("Sms to [+380991234567] | Category: tech | 50% OFF!", smsUser.getNotifications().get(0));
        assertEquals("Telegram to [@sasha] | Category: tech | 50% OFF!", tgUser.getNotifications().get(0));
    }

    @Test
    void shouldRejectInvalidContactFormats() {
        assertThrows(IllegalArgumentException.class, () -> new EmailSubscriber("not-email"));
        assertThrows(IllegalArgumentException.class, () -> new SmsSubscriber("hello"));
        assertThrows(IllegalArgumentException.class, () -> new TelegramSubscriber("sasha"));
    }

    @Test
    void subscriberUpdateShouldRejectInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> emailUser.update(null, "Sale"));
        assertThrows(IllegalArgumentException.class, () -> emailUser.update("laptops", "   "));
    }

    @Test
    void unsubscribeFromNonExistentCategoryShouldNotThrowException() {
        assertDoesNotThrow(() -> store.unsubscribe("cars", emailUser));
    }

    @Test
    void shouldIgnoreDuplicateSubscriptions() {
        store.subscribe("laptops", emailUser);
        store.subscribe("laptops", emailUser);

        store.announceDiscount("laptops", "Sale");

        assertEquals(1, emailUser.getNotifications().size());
    }

    @Test
    void notificationsListShouldBeImmutable() {
        store.subscribe("laptops", emailUser);
        store.announceDiscount("laptops", "Sale");

        assertThrows(UnsupportedOperationException.class,
                () -> emailUser.getNotifications().add("Fake Notification")
        );
    }
}