package org.example.factory;

import org.example.models.story.Conflict;
import org.example.models.story.Ending;
import org.example.models.story.Hero;
import org.example.models.story.Villain;

public interface StoryFactory {
    Hero createHero();
    Villain createVillain();
    Conflict createConflict();
    Ending createEnding();
}
