package org.example;

import java.util.Collections;
import java.util.Queue;

public class FoolDealingStrategy implements DealingStrategy {
    @Override
    public DealResult deal(Queue<Card> deck, int numberOfPlayers) {
        return new CustomDealingStrategy(6, Collections.singletonMap("Trump card", 1)).deal(deck, numberOfPlayers);
    }
}
