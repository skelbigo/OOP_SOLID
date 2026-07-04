package org.example.strategy.impl;

import org.example.strategy.AttackStrategy;

public class MagicAttack implements AttackStrategy {
    @Override
    public int calculateDamage() {
        return 40;
    }

    @Override
    public String getAttackType() {
        return "Magic Spell";
    }
}
