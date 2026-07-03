package org.example.factory.fairytale;

import org.example.models.story.Hero;

public class FairyTaleHero implements Hero {
    @Override
    public String describe() {
        return "Prince or Princess";
    }
}
