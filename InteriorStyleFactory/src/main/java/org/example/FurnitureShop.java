package org.example;

import org.example.factory.FurnitureFactory;
import org.example.models.furniture.Chair;
import org.example.models.furniture.Sofa;
import org.example.models.furniture.Table;

public class FurnitureShop {
    private final Chair chair;
    private final Table table;
    private final Sofa sofa;

    public FurnitureShop(FurnitureFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        this.chair = factory.createChair();
        this.table = factory.createTable();
        this.sofa = factory.createSofa();
    }

    public Chair getChair() { return chair; }
    public Table getTable() { return table; }
    public Sofa getSofa() { return sofa; }
}
