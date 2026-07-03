package org.example.factory.superhero;

import org.example.models.story.Conflict;

public class SuperheroConflict implements Conflict {
    @Override
    public String describe() {
        return "Global Crisis";
    }
}
