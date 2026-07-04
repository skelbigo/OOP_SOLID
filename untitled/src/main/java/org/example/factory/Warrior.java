package org.example.factory;

import org.example.models.BaseHero;
import org.example.strategy.impl.MeleeAttack;

public class Warrior extends BaseHero {
    public Warrior(String name) { super(name, 150, new MeleeAttack()); }
}