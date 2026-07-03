package org.example.models.furniture.modern;

import org.example.models.furniture.Chair;

public class ModernChair implements Chair {
    @Override
    public String sitOn() {
        return "Sitting on modern chair.";
    }
}
