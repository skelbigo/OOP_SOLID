package org.example.observer;

public interface HeroObserver {
    void onDamageTaken(String heroName, int damage, int remainingHp);
    void onEnemyDefeated(String heroName);
    void onLevelUp(String heroName, int newLevel);
}
