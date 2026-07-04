package org.example.decorator;

import org.example.models.Hero;
import org.example.observer.HeroObserver;

public abstract class HeroBuffDecorator implements Hero {
    protected final Hero wrapper;

    protected HeroBuffDecorator(Hero wrapper) {
        if (wrapper == null) throw new IllegalArgumentException("Hero to decorate cannot be null");
        this.wrapper = wrapper;
    }

    @Override
    public String getName() {
        return wrapper.getName();
    }

    @Override
    public int getHp() {
        return wrapper.getHp();
    }

    @Override
    public int getLevel() {
        return wrapper.getLevel();
    }

    @Override public int attack() {
        return wrapper.attack();
    }

    @Override public void takeDamage(int damage) {
        wrapper.takeDamage(damage);
    }

    @Override public void defeatEnemy() {
        wrapper.defeatEnemy();
    }

    @Override public void subscribe(HeroObserver observer) {
        wrapper.subscribe(observer);
    }
}
