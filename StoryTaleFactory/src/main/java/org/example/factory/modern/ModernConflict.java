package org.example.factory.modern;

import org.example.models.story.Conflict;

public class ModernConflict implements Conflict {
    @Override
    public String describe() {
        return "A Difficult Personal Choice";
    }
}
