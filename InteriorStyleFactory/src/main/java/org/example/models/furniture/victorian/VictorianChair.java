package org.example.models.furniture.victorian;

import org.example.models.furniture.Chair;

public class VictorianChair implements Chair {
    @Override
    public String sitOn() {
        return "Sitting on victorian chair.";
    }
}
