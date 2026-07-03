package org.example;

import org.example.factory.StoryFactory;
import org.example.models.story.*;

public class StoryTeller {
    private final Hero hero;
    private final Villain villain;
    private final Conflict conflict;
    private final Ending ending;

    public StoryTeller(StoryFactory storyFactory) {
        if (storyFactory == null) {
            throw new IllegalArgumentException("Story factory cannot be null");
        }
        this.hero = storyFactory.createHero();
        this.villain = storyFactory.createVillain();
        this.conflict = storyFactory.createConflict();
        this.ending = storyFactory.createEnding();
    }

    public Hero getHero() {
        return hero;
    }

    public Villain getVillain() {
        return villain;
    }

    public Conflict getConflict() {
        return conflict;
    }

    public Ending getEnding() {
        return ending;
    }

    @Override
    public String toString() {
        return "StoryTeller{" +
                "hero=" + hero.describe() +
                ", villain=" + villain.describe() +
                ", conflict=" + conflict.describe() +
                ", ending=" + ending.describe() +
                '}';
    }
}
