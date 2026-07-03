package org.example.factory.superhero;

import org.example.models.story.Hero;

public class Superhero implements Hero {
    @Override
    public String describe() {
        return "Superhero";
    }
}
