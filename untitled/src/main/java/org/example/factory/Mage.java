package org.example.factory;

import org.example.models.BaseHero;
import org.example.strategy.impl.MagicAttack;

public class Mage extends BaseHero {
    public Mage(String name) { super(name, 80, new MagicAttack()); }
}
