package org.example.factory.superhero;

import org.example.models.story.Villain;

public class Supervillain implements Villain {
    @Override
    public String describe() {
        return "Supervillain";
    }
}
