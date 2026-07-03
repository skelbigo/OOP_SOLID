package org.example.factory.minimalistic;

import org.example.models.furniture.minimalistic.MinimalisticSofa;
import org.example.models.furniture.minimalistic.MinimalisticTable;
import org.example.factory.FurnitureFactory;
import org.example.models.furniture.Chair;
import org.example.models.furniture.Sofa;
import org.example.models.furniture.Table;
import org.example.models.furniture.minimalistic.MinimalisticChair;

public class MinimalisticFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new MinimalisticChair();
    }

    @Override
    public Table createTable() {
        return new MinimalisticTable();
    }

    @Override
    public Sofa createSofa() {
        return new MinimalisticSofa();
    }
}