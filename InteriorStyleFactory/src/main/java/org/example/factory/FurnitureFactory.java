package org.example.factory;

import org.example.models.furniture.Chair;
import org.example.models.furniture.Sofa;
import org.example.models.furniture.Table;

public interface FurnitureFactory {
    Chair createChair();
    Table createTable();
    Sofa createSofa();
}
