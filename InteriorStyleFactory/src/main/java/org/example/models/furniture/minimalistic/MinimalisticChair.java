package org.example.models.furniture.minimalistic;

import org.example.models.furniture.Chair;

public class MinimalisticChair implements Chair {
    @Override
    public String sitOn() {
        return "Sitting on minimalistic chair.";
    }
}
