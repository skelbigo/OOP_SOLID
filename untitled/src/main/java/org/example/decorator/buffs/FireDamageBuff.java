package org.example.decorator.buffs;

import org.example.decorator.HeroBuffDecorator;
import org.example.models.Hero;

public class FireDamageBuff extends HeroBuffDecorator {
    public FireDamageBuff(Hero wrapper) { super(wrapper); }

    @Override
    public int attack() {
        return wrapper.attack() + 15;
    }
}
