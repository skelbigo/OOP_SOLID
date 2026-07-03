package org.example;

import java.util.Queue;

public class Dealer {
    private DealingStrategy dealingStrategy;

    public Dealer(DealingStrategy dealingStrategy) {
        if (dealingStrategy == null) {
            throw new IllegalArgumentException("Dealing strategy cannot be null");
        }
        this.dealingStrategy = dealingStrategy;
    }

    public void setDealingStrategy(DealingStrategy dealingStrategy) {
        if (dealingStrategy == null) {
            throw new IllegalArgumentException("Dealing strategy cannot be null");
        }
        this.dealingStrategy = dealingStrategy;
    }

    public DealResult dealCards(Queue<Card> deck, int numberOfPlayers) {
        if (deck == null || numberOfPlayers < 1) {
            throw new IllegalArgumentException("Invalid deck or players count");
        }

        DealResult result = dealingStrategy.deal(deck, numberOfPlayers);
        while (!deck.isEmpty()) {
            result.addCardToExtraStack("Remaining", deck.poll());
        }
        return result;
    }
}
