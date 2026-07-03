package org.example.factory.fairytale;

import org.example.models.story.Ending;

public class FairyTaleEnding implements Ending {
    @Override
    public String describe() {
        return "Happy Ending";
    }
}
