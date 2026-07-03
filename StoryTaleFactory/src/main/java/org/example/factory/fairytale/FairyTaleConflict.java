package org.example.factory.fairytale;

import org.example.models.story.Conflict;

public class FairyTaleConflict implements Conflict {
    @Override
    public String describe() {
        return "A Terrible Curse";
    }
}
