package org.example;

import java.util.Map;
import java.util.Queue;

public class CustomDealingStrategy implements DealingStrategy {
    private final int cardsPerPlayer;
    private final Map<String, Integer> extraStacksConfig;

    public CustomDealingStrategy(int cardsPerPlayer, Map<String, Integer> extraStacksConfig) {
        this.cardsPerPlayer = cardsPerPlayer;
        this.extraStacksConfig = extraStacksConfig != null ? extraStacksConfig : Map.of();
    }

    @Override
    public DealResult deal(Queue<Card> deck, int numberOfPlayers) {
        int extraCards = extraStacksConfig.values().stream().mapToInt(Integer::intValue).sum();
        int requiredCards = (numberOfPlayers * cardsPerPlayer) + extraCards;

        if (deck.size() < requiredCards) {
            throw new IllegalArgumentException("Not enough cards in the deck");
        }

        DealResult result = new DealResult(numberOfPlayers);

        for (int round = 0; round < cardsPerPlayer; round++) {
            for (int player = 1; player <= numberOfPlayers; player++) {
                result.addCardToPlayer(player, deck.poll());
            }
        }

        for (Map.Entry<String, Integer> stackConfig : extraStacksConfig.entrySet()) {
            String stackName = stackConfig.getKey();
            int cardsCount = stackConfig.getValue();
            for (int i = 0; i < cardsCount; i++) {
                result.addCardToExtraStack(stackName, deck.poll());
            }
        }

        return result;
    }
}
