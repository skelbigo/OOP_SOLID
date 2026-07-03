package org.example.factory.modern;

import org.example.models.story.Ending;

public class ModernEnding implements Ending {
    @Override
    public String describe() {
        return "Open Final";
    }
}
