package org.example.factory.modern;

import org.example.factory.FurnitureFactory;
import org.example.models.furniture.Chair;
import org.example.models.furniture.Sofa;
import org.example.models.furniture.Table;
import org.example.models.furniture.modern.ModernChair;
import org.example.models.furniture.modern.ModernSofa;
import org.example.models.furniture.modern.ModernTable;

public class ModernFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}
