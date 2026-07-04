package org.example.strategy.impl;
import org.example.strategy.AttackStrategy;

public class MeleeAttack implements AttackStrategy {
    @Override
    public int calculateDamage() {
        return 25;
    }

    @Override
    public String getAttackType() {
        return "Melee Strike";
    }
}