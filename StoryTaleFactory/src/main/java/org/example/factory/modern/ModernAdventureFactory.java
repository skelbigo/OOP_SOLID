package org.example.factory.modern;

import org.example.factory.StoryFactory;
import org.example.models.story.Conflict;
import org.example.models.story.Ending;
import org.example.models.story.Hero;
import org.example.models.story.Villain;

public class ModernAdventureFactory implements StoryFactory {
    @Override
    public Hero createHero() {
        return new ModernHero();
    }

    @Override
    public Villain createVillain() {
        return new ModernVillain();
    }

    @Override
    public Conflict createConflict() {
        return new ModernConflict();
    }

    @Override
    public Ending createEnding() {
        return new ModernEnding();
    }
}
