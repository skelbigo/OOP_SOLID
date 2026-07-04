package org.example.strategy.impl;

import org.example.strategy.AttackStrategy;

public class RangedAttack implements AttackStrategy {
    @Override
    public int calculateDamage() {
        return 20;
    }

    @Override
    public String getAttackType() {
        return "Ranged Shot";
    }
}
