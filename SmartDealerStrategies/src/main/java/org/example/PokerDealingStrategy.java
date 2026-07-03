package org.example;

import java.util.Collections;
import java.util.Queue;

public class PokerDealingStrategy implements DealingStrategy {
    @Override
    public DealResult deal(Queue<Card> deck, int numberOfPlayers) {
        return new CustomDealingStrategy(5, Collections.emptyMap()).deal(deck, numberOfPlayers);
    }
}
