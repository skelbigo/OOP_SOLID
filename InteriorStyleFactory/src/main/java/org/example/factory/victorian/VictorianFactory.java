package org.example.factory.victorian;

import org.example.models.furniture.victorian.VictorianSofa;
import org.example.models.furniture.victorian.VictorianTable;
import org.example.factory.FurnitureFactory;
import org.example.models.furniture.Chair;
import org.example.models.furniture.Sofa;
import org.example.models.furniture.Table;
import org.example.models.furniture.victorian.VictorianChair;

public class VictorianFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Table createTable() {
        return new VictorianTable();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }
}
