package org.example.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogNotifier implements HeroObserver {
    private final List<String> battleLogs = new ArrayList<>();

    public List<String> getBattleLogs() {
        return Collections.unmodifiableList(battleLogs);
    }

    @Override
    public void onDamageTaken(String heroName, int damage, int remainingHp) {
        validateHeroName(heroName);
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        if (remainingHp < 0) {
            throw new IllegalArgumentException("Remaining HP cannot be negative");
        }

        battleLogs.add(String.format("[%s] took %d damage! HP left: %d", heroName, damage, remainingHp));
    }

    @Override
    public void onEnemyDefeated(String heroName) {
        validateHeroName(heroName);
        battleLogs.add(String.format("VICTORY! [%s] defeated an enemy!", heroName));
    }

    @Override
    public void onLevelUp(String heroName, int newLevel) {
        validateHeroName(heroName);
        if (newLevel <= 1) {
            throw new IllegalArgumentException("New level must be greater than 1");
        }

        battleLogs.add(String.format("LEVEL UP! [%s] reached level %d!", heroName, newLevel));
    }

    private void validateHeroName(String heroName) {
        if (heroName == null || heroName.strip().isBlank()) {
            throw new IllegalArgumentException("Hero name cannot be blank");
        }
    }
}