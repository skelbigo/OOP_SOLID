package org.example.factory;

import org.example.models.BaseHero;
import org.example.strategy.impl.RangedAttack;

public class Archer extends BaseHero {
    public Archer(String name) { super(name, 100, new RangedAttack()); }
}
