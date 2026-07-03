package org.example.factory.modern;

import org.example.models.story.Villain;

public class ModernVillain implements Villain {
    @Override
    public String describe() {
        return "A corrupt system or a problem in the city";
    }
}
