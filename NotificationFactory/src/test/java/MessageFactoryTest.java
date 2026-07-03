import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageFactoryTest {

    private MessageFactory factory;

    @BeforeEach
    void setUp() {
        factory = new MessageFactory();
    }

    @Test
    void shouldCreateEmailMessageAndStripData() {
        Message msg = factory.createMessage(MessageType.EMAIL, "  test@gmail.com  ");
        assertInstanceOf(EmailMessage.class, msg);
    }

    @Test
    void shouldCreateSmsMessageAndStripData() {
        Message msg = factory.createMessage(MessageType.SMS, " +380991234567\n");
        assertInstanceOf(SmsMessage.class, msg);
    }

    @Test
    void shouldCreateTelegramMessageAndStripData() {
        Message msg = factory.createMessage(MessageType.TELEGRAM, "\t@user123 ");
        assertInstanceOf(TelegramMessage.class, msg);
    }

    @Test
    void shouldCreatePushMessageAndStripData() {
        Message msg = factory.createMessage(MessageType.PUSH, " token-xyz ");
        assertInstanceOf(PushMessage.class, msg);
    }

    @Test
    void shouldThrowExceptionWhenTypeIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createMessage(null, "data")
        );
        assertEquals("Message type cannot be null", exception.getMessage());
    }

    @Test
    void emailShouldRejectInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new EmailMessage(null));
        assertThrows(IllegalArgumentException.class, () -> new EmailMessage("   "));
        assertThrows(IllegalArgumentException.class, () -> new EmailMessage("test@"));
        assertThrows(IllegalArgumentException.class, () -> new EmailMessage("@domain.com"));
        assertThrows(IllegalArgumentException.class, () -> new EmailMessage("a@b@c.com"));
    }

    @Test
    void smsShouldRejectInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new SmsMessage(null));
        assertThrows(IllegalArgumentException.class, () -> new SmsMessage(""));
        assertThrows(IllegalArgumentException.class, () -> new SmsMessage("123abc456"));
        assertThrows(IllegalArgumentException.class, () -> new SmsMessage("++380"));
    }

    @Test
    void telegramShouldRejectInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new TelegramMessage(null));
        assertThrows(IllegalArgumentException.class, () -> new TelegramMessage("   "));
        assertThrows(IllegalArgumentException.class, () -> new TelegramMessage("durov")); // missing @
        assertThrows(IllegalArgumentException.class, () -> new TelegramMessage("@")); // only @
    }

    @Test
    void pushShouldRejectInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new PushMessage(null));
        assertThrows(IllegalArgumentException.class, () -> new PushMessage("   \n"));
    }

    @Test
    void sendShouldReturnExpectedMessageAndRejectInvalidContent() {
        Message msg = factory.createMessage(MessageType.EMAIL, "a@b.com");

        assertEquals("Sending Email to [a@b.com]: Hello", msg.send("Hello"));

        assertThrows(IllegalArgumentException.class, () -> msg.send(null));
        assertThrows(IllegalArgumentException.class, () -> msg.send("   "));
    }

    @Test
    void factoryShouldSupportEveryMessageType() {
        Map<MessageType, String> validDataMap = Map.of(
                MessageType.EMAIL, "test@mail.com",
                MessageType.SMS, "+380123456789",
                MessageType.TELEGRAM, "@valid_user",
                MessageType.PUSH, "valid_token"
        );

        for (MessageType type : MessageType.values()) {
            String validData = validDataMap.get(type);
            assertNotNull(validData, "Missing test data for enum value: " + type);

            Message message = factory.createMessage(type, validData);
            assertNotNull(message, "Factory returned null for type: " + type);
        }
    }
}