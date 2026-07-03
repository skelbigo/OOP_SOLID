package org.example.factory.modern;

import org.example.models.story.Hero;

public class ModernHero implements Hero {
    @Override
    public String describe() {
        return "An ordinary person";
    }
}
