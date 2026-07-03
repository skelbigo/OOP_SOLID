package org.example;

import java.util.*;

public class DealResult {
    private final Map<Integer, List<Card>> playerHands = new LinkedHashMap<>();
    private final Map<String, List<Card>> extraStacks = new LinkedHashMap<>();

    public DealResult(int numberOfPlayer) {
        for (int i = 1; i <= numberOfPlayer; i++) {
            playerHands.put(i, new ArrayList<>());
        }
    }

    public void addCardToPlayer(int playerNumber, Card card) {
        playerHands.get(playerNumber).add(card);
    }

    public void addCardToExtraStack(String stackName, Card card) {
        extraStacks.computeIfAbsent(stackName, k -> new ArrayList<>()).add(card);
    }

    public Map<Integer, List<Card>> getPlayerHands() {
        Map<Integer, List<Card>> readOnlyHands = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<Card>> entry : playerHands.entrySet()) {
            readOnlyHands.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        return Collections.unmodifiableMap(readOnlyHands);
    }

    public Map<String, List<Card>> getExtraStacks() {
        Map<String, List<Card>> readOnlyStacks = new LinkedHashMap<>();
        for (Map.Entry<String, List<Card>> entry : extraStacks.entrySet()) {
            readOnlyStacks.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        return Collections.unmodifiableMap(readOnlyStacks);
    }
}
