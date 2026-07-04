package org.example.models;

import org.example.observer.HeroObserver;

public interface Hero {
    String getName();
    int getHp();
    int getLevel();
    int attack();
    void takeDamage(int damage);
    void defeatEnemy();
    void subscribe(HeroObserver observer);
}