import org.example.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {
    private Queue<Card> deck;

    @BeforeEach
    void setup() {
        deck = new LinkedList<>();
        for (int i = 1; i <= 20; i++) {
            deck.add(new Card("Card " + i));
        }
    }

    @Test
    void shouldDealPokerCardsRoundRobinForTwoPlayers() {
        Dealer dealer = new Dealer(new PokerDealingStrategy());
        DealResult result = dealer.dealCards(deck, 2);

        List<Card> player1 = result.getPlayerHands().get(1);
        List<Card> player2 = result.getPlayerHands().get(2);

        assertEquals(5, player1.size());
        assertEquals(5, player2.size());

        assertEquals("Card 1", player1.get(0).name());
        assertEquals("Card 3", player1.get(1).name());
        assertEquals("Card 5", player1.get(2).name());
        assertEquals("Card 7", player1.get(3).name());
        assertEquals("Card 9", player1.get(4).name());

        assertEquals("Card 2", player2.get(0).name());
        assertEquals("Card 4", player2.get(1).name());
        assertEquals("Card 6", player2.get(2).name());
        assertEquals("Card 8", player2.get(3).name());
        assertEquals("Card 10", player2.get(4).name());

        assertEquals(10, result.getExtraStacks().get("Remaining").size());
        assertTrue(deck.isEmpty());
    }

    @Test
    void shouldDealTexasHoldemCorrectly() {
        Dealer dealer = new Dealer(new TexasHoldemDealingStrategy());
        DealResult result = dealer.dealCards(deck, 2);

        List<Card> player1 = result.getPlayerHands().get(1);
        List<Card> player2 = result.getPlayerHands().get(2);

        assertEquals(2, player1.size());
        assertEquals(2, player2.size());

        assertEquals("Card 1", player1.get(0).name());
        assertEquals("Card 3", player1.get(1).name());

        assertEquals("Card 2", player2.get(0).name());
        assertEquals("Card 4", player2.get(1).name());

        List<Card> community = result.getExtraStacks().get("Community");
        assertNotNull(community);
        assertEquals(5, community.size());

        assertEquals("Card 5", community.get(0).name());
        assertEquals("Card 6", community.get(1).name());
        assertEquals("Card 7", community.get(2).name());
        assertEquals("Card 8", community.get(3).name());
        assertEquals("Card 9", community.get(4).name());

        assertEquals(11, result.getExtraStacks().get("Remaining").size());
        assertTrue(deck.isEmpty());
    }

    @Test
    void shouldDealFoolCorrectly() {
        Dealer dealer = new Dealer(new FoolDealingStrategy());
        DealResult result = dealer.dealCards(deck, 2);

        List<Card> player1 = result.getPlayerHands().get(1);
        List<Card> player2 = result.getPlayerHands().get(2);

        assertEquals(6, player1.size());
        assertEquals(6, player2.size());

        assertEquals("Card 1", player1.get(0).name());
        assertEquals("Card 3", player1.get(1).name());
        assertEquals("Card 5", player1.get(2).name());
        assertEquals("Card 7", player1.get(3).name());
        assertEquals("Card 9", player1.get(4).name());
        assertEquals("Card 11", player1.get(5).name());

        assertEquals("Card 2", player2.get(0).name());
        assertEquals("Card 4", player2.get(1).name());
        assertEquals("Card 6", player2.get(2).name());
        assertEquals("Card 8", player2.get(3).name());
        assertEquals("Card 10", player2.get(4).name());
        assertEquals("Card 12", player2.get(5).name());

        List<Card> trumpCard = result.getExtraStacks().get("Trump card");
        assertNotNull(trumpCard);
        assertEquals(1, trumpCard.size());
        assertEquals("Card 13", trumpCard.get(0).name());

        assertEquals(7, result.getExtraStacks().get("Remaining").size());
        assertTrue(deck.isEmpty());
    }


    @Test
    void shouldDealCustomGameCorrectly() {
        Map<String, Integer> extraStacks = new LinkedHashMap<>();
        extraStacks.put("Discard Pile", 2);
        extraStacks.put("Reserve", 3);

        Dealer dealer = new Dealer(new CustomDealingStrategy(3, extraStacks));
        DealResult result = dealer.dealCards(deck, 2);

        List<Card> player1 = result.getPlayerHands().get(1);
        List<Card> player2 = result.getPlayerHands().get(2);

        assertEquals(3, player1.size());
        assertEquals(3, player2.size());

        assertEquals("Card 1", player1.get(0).name());
        assertEquals("Card 3", player1.get(1).name());
        assertEquals("Card 5", player1.get(2).name());

        assertEquals("Card 2", player2.get(0).name());
        assertEquals("Card 4", player2.get(1).name());
        assertEquals("Card 6", player2.get(2).name());

        List<Card> discardPile = result.getExtraStacks().get("Discard Pile");
        assertNotNull(discardPile);
        assertEquals(2, discardPile.size());
        assertEquals("Card 7", discardPile.get(0).name());
        assertEquals("Card 8", discardPile.get(1).name());

        List<Card> reserve = result.getExtraStacks().get("Reserve");
        assertNotNull(reserve);
        assertEquals(3, reserve.size());
        assertEquals("Card 9", reserve.get(0).name());
        assertEquals("Card 10", reserve.get(1).name());
        assertEquals("Card 11", reserve.get(2).name());

        assertEquals(9, result.getExtraStacks().get("Remaining").size());
        assertTrue(deck.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenDeckIsNull() {
        Dealer dealer = new Dealer(new PokerDealingStrategy());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> dealer.dealCards(null, 2)
        );
        assertEquals("Invalid deck or players count", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPlayersCountIsLessThanOne() {
        Dealer dealer = new Dealer(new PokerDealingStrategy());

        IllegalArgumentException exceptionZero = assertThrows(IllegalArgumentException.class,
                () -> dealer.dealCards(deck, 0)
        );
        assertEquals("Invalid deck or players count", exceptionZero.getMessage());

        IllegalArgumentException exceptionNegative = assertThrows(IllegalArgumentException.class,
                () -> dealer.dealCards(deck, -5)
        );
        assertEquals("Invalid deck or players count", exceptionNegative.getMessage());
    }

    @Test
    void shouldThrowExceptionInPokerWhenNotEnoughCards() {
        Queue<Card> smallDeck = new LinkedList<>();
        for (int i = 1; i <= 9; i++) smallDeck.add(new Card("Card " + i));

        Dealer dealer = new Dealer(new PokerDealingStrategy());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> dealer.dealCards(smallDeck, 2)
        );
        assertEquals("Not enough cards in the deck", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionInTexasHoldemWhenNotEnoughCards() {
        Queue<Card> smallDeck = new LinkedList<>();
        for (int i = 1; i <= 8; i++) smallDeck.add(new Card("Card " + i));

        Dealer dealer = new Dealer(new TexasHoldemDealingStrategy());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> dealer.dealCards(smallDeck, 2)
        );
        assertEquals("Not enough cards in the deck", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionInFoolWhenNotEnoughCards() {
        Queue<Card> smallDeck = new LinkedList<>();
        for (int i = 1; i <= 12; i++) smallDeck.add(new Card("Card " + i));

        Dealer dealer = new Dealer(new FoolDealingStrategy());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> dealer.dealCards(smallDeck, 2)
        );
        assertEquals("Not enough cards in the deck", exception.getMessage());
    }

    @Test
    void shouldProtectDealResultFromExternalModifications() {
        Dealer dealer = new Dealer(new PokerDealingStrategy());
        DealResult result = dealer.dealCards(deck, 2);

        assertThrows(UnsupportedOperationException.class, () -> result.getPlayerHands().clear());
        assertThrows(UnsupportedOperationException.class, () -> result.getPlayerHands().get(1).add(new Card("Cheat Card")));
        assertThrows(UnsupportedOperationException.class, () -> result.getExtraStacks().put("Hack", new ArrayList<>()));
    }

    @Test
    void shouldThrowExceptionWhenStrategyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Dealer(null));

        Dealer dealer = new Dealer(new PokerDealingStrategy());
        assertThrows(IllegalArgumentException.class, () -> dealer.setDealingStrategy(null));
    }

    @Test
    void shouldMoveRestOfDeckToRemainingStack() {
        Dealer dealer = new Dealer(new PokerDealingStrategy());
        DealResult result = dealer.dealCards(deck, 2);

        List<Card> remaining = result.getExtraStacks().get("Remaining");
        assertNotNull(remaining);
        assertEquals(10, remaining.size());

        assertEquals("Card 11", remaining.get(0).name());
        assertEquals("Card 20", remaining.get(9).name());

        assertTrue(deck.isEmpty());
    }

    @Test
    void customStrategyShouldValidateRequiredCardsCorrectly() {
        Map<String, Integer> extraStacks = Map.of("BigStack", 10);
        Dealer dealer = new Dealer(new CustomDealingStrategy(5, extraStacks));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> dealer.dealCards(deck, 3)
        );
        assertEquals("Not enough cards in the deck", exception.getMessage());
    }
}
