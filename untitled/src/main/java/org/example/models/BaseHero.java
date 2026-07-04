package org.example.models;

import org.example.observer.HeroObserver;
import org.example.strategy.AttackStrategy;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class BaseHero implements Hero {
    private final String name;
    private int hp;
    private int level;
    private final AttackStrategy attackStrategy;
    private final Set<HeroObserver> observers = new LinkedHashSet<>();

    protected BaseHero(String name, int baseHp, AttackStrategy attackStrategy) {
        if (name == null || name.strip().isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (attackStrategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        if (baseHp <= 0) {
            throw new IllegalArgumentException("Base HP must be greater than zero");
        }

        this.name = name.strip();
        this.hp = baseHp;
        this.level = 1;
        this.attackStrategy = attackStrategy;
    }

    @Override public String getName() {
        return name;
    }

    @Override public int getHp() {
        return hp;
    }

    @Override public int getLevel() {
        return level;
    }

    @Override
    public void subscribe(HeroObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        observers.add(observer);
    }

    @Override
    public int attack() {
        return attackStrategy.calculateDamage();
    }

    @Override
    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.hp = Math.max(0, this.hp - damage);
        for (HeroObserver obs : observers) {
            obs.onDamageTaken(getName(), damage, this.hp);
        }
    }

    @Override
    public void defeatEnemy() {
        for (HeroObserver obs : observers) {
            obs.onEnemyDefeated(getName());
        }
        levelUp();
    }

    private void levelUp() {
        this.level++;
        this.hp += 50;
        for (HeroObserver obs : observers) {
            obs.onLevelUp(getName(), this.level);
        }
    }
}