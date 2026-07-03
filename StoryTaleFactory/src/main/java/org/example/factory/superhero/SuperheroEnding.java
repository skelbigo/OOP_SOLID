package org.example.factory.superhero;

import org.example.models.story.Ending;

public class SuperheroEnding implements Ending {
    @Override
    public String describe() {
        return "Victory at the cost of great sacrifice";
    }
}
