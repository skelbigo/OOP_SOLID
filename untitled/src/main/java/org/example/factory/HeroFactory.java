package org.example.factory;

import org.example.models.Hero;

public class HeroFactory {
    public Hero createHero(HeroType type, String name) {
        if (type == null) throw new IllegalArgumentException("Hero type cannot be null");

        return switch (type) {
            case WARRIOR -> new Warrior(name);
            case MAGE -> new Mage(name);
            case ARCHER -> new Archer(name);
        };
    }
}
