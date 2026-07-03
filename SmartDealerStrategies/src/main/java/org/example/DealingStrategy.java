package org.example;

import java.util.Queue;

public interface DealingStrategy {
    DealResult deal(Queue<Card> deck, int numberOfPlayers);
}
