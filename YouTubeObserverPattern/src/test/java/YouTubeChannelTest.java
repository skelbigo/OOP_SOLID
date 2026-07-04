import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YouTubeChannelTest {

    private YouTubeChannel channel;
    private User sasha;
    private User valya;

    @BeforeEach
    void setUp() {
        channel = new YouTubeChannel("BLOGMAYSTER");
        sasha = new User("Sasha");
        valya = new User("Valya");
    }

    @Test
    void shouldNotifyAllSubscribersWhenVideoIsPublished() {
        channel.subscribe(sasha);
        channel.subscribe(valya);
        channel.publishVideo("Video 1");

        assertEquals(1, sasha.getNotifications().size());
        assertEquals("New video from BLOGMAYSTER: Video 1", sasha.getNotifications().get(0));
    }

    @Test
    void shouldNotNotifyUnsubscribedUsers() {
        channel.subscribe(sasha);
        channel.subscribe(valya);
        channel.publishVideo("Video 1");

        channel.unsubscribe(valya);
        channel.publishVideo("Video 2");

        assertEquals(2, sasha.getNotifications().size());
        assertEquals(1, valya.getNotifications().size());
    }

    @Test
    void shouldStripDataCorrectly() {
        YouTubeChannel messyChannel = new YouTubeChannel("    BLOGMAYSTER   ");
        User messyUser = new User("   Sasha   ");

        messyChannel.subscribe(messyUser);
        messyChannel.publishVideo("    Video 1   ");

        assertEquals("New video from BLOGMAYSTER: Video 1", messyUser.getNotifications().get(0));
        assertEquals("Sasha", messyUser.getName());
    }

    @Test
    void shouldThrowExceptionForInvalidChannelOrUserName() {
        assertThrows(IllegalArgumentException.class, () -> new YouTubeChannel(null));
        assertThrows(IllegalArgumentException.class, () -> new YouTubeChannel("   "));

        assertThrows(IllegalArgumentException.class, () -> new User(null));
        assertThrows(IllegalArgumentException.class, () -> new User("   "));
    }

    @Test
    void shouldThrowExceptionWhenSubscribingOrUnsubscribingNull() {
        assertThrows(IllegalArgumentException.class, () -> channel.subscribe(null));
        assertThrows(IllegalArgumentException.class, () -> channel.unsubscribe(null));
    }

    @Test
    void shouldThrowExceptionWhenPublishingInvalidVideoTitle() {
        assertThrows(IllegalArgumentException.class, () -> channel.publishVideo(null));
        assertThrows(IllegalArgumentException.class, () -> channel.publishVideo("   "));
    }

    @Test
    void shouldProtectNotificationsListFromExternalModification() {
        channel.subscribe(sasha);
        channel.publishVideo("Test Video");

        List<String> notifications = sasha.getNotifications();

        assertThrows(UnsupportedOperationException.class, () -> notifications.add("Fake video"));
    }

    @Test
    void shouldNotAddDuplicateSubscribers() {
        channel.subscribe(sasha);
        channel.subscribe(sasha);

        channel.publishVideo("Video 1");

        assertEquals(1, sasha.getNotifications().size());
    }
}