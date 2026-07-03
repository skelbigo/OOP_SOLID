package org.example.factory.fairytale;

import org.example.models.story.Villain;

public class FairyTaleVillain implements Villain {
    @Override
    public String describe() {
        return "The Evil Sorcerer";
    }
}
