package org.example.factory.superhero;

import org.example.factory.StoryFactory;
import org.example.models.story.Conflict;
import org.example.models.story.Ending;
import org.example.models.story.Hero;
import org.example.models.story.Villain;

public class SuperheroStoryFactory implements StoryFactory {
    @Override
    public Hero createHero() {
        return new Superhero();
    }

    @Override
    public Villain createVillain() {
        return new Supervillain();
    }

    @Override
    public Conflict createConflict() {
        return new SuperheroConflict();
    }

    @Override
    public Ending createEnding() {
        return new SuperheroEnding();
    }
}
