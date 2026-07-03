package org.example;

import java.util.Collections;
import java.util.Queue;

public class TexasHoldemDealingStrategy implements DealingStrategy {
    @Override
    public DealResult deal(Queue<Card> deck, int numberOfPlayers) {
        return new CustomDealingStrategy(2, Collections.singletonMap("Community", 5)).deal(deck, numberOfPlayers);
    }
}
