package org.example.decorator.buffs;

import org.example.decorator.HeroBuffDecorator;
import org.example.models.Hero;

public class ShieldBuff extends HeroBuffDecorator {
    public ShieldBuff(Hero wrapper) { super(wrapper); }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = Math.max(0, damage - 10);
        wrapper.takeDamage(reducedDamage);
    }
}
