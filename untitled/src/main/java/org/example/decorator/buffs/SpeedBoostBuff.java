package org.example.decorator.buffs;

import org.example.decorator.HeroBuffDecorator;
import org.example.models.Hero;

public class SpeedBoostBuff extends HeroBuffDecorator {
    public SpeedBoostBuff(Hero wrapper) { super(wrapper); }

    @Override
    public String getName() {
        return "Swift " + wrapper.getName();
    }
}