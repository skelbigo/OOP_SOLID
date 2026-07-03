package org.example.factory.fairytale;

import org.example.factory.StoryFactory;
import org.example.models.story.Conflict;
import org.example.models.story.Ending;
import org.example.models.story.Hero;
import org.example.models.story.Villain;

public class ClassicFairyTaleFactory implements StoryFactory {
    @Override
    public Hero createHero() {
        return new FairyTaleHero();
    }

    @Override
    public Villain createVillain() {
        return new FairyTaleVillain();
    }

    @Override
    public Conflict createConflict() {
        return new FairyTaleConflict();
    }

    @Override
    public Ending createEnding() {
        return new FairyTaleEnding();
    }
}
