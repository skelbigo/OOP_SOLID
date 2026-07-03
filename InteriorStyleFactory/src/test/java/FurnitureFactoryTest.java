import org.example.FurnitureShop;
import org.example.factory.*;
import org.example.factory.modern.ModernFactory;
import org.example.factory.victorian.VictorianFactory;
import org.example.factory.minimalistic.MinimalisticFactory;
import org.example.models.furniture.modern.*;
import org.example.models.furniture.victorian.*;
import org.example.models.furniture.minimalistic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FurnitureFactoryTest {

    @Test
    void modernFactoryShouldCreateModernFurniture() {
        FurnitureFactory factory = new ModernFactory();

        assertInstanceOf(ModernChair.class, factory.createChair());
        assertInstanceOf(ModernTable.class, factory.createTable());
        assertInstanceOf(ModernSofa.class, factory.createSofa());

        assertEquals("Sitting on modern chair.", factory.createChair().sitOn());
    }

    @Test
    void victorianFactoryShouldCreateVictorianFurniture() {
        FurnitureFactory factory = new VictorianFactory();

        assertInstanceOf(VictorianChair.class, factory.createChair());
        assertInstanceOf(VictorianTable.class, factory.createTable());
        assertInstanceOf(VictorianSofa.class, factory.createSofa());

        assertEquals("Lying on victorian sofa.", factory.createSofa().lieOn());
    }

    @Test
    void minimalisticFactoryShouldCreateMinimalisticFurniture() {
        FurnitureFactory factory = new MinimalisticFactory();

        assertInstanceOf(MinimalisticChair.class, factory.createChair());
        assertInstanceOf(MinimalisticTable.class, factory.createTable());
        assertInstanceOf(MinimalisticSofa.class, factory.createSofa());

        assertEquals("Putting things on minimalistic table.", factory.createTable().putThingsOn());
    }

    @Test
    void furnitureShopShouldWorkWithAnyFactory() {
        FurnitureShop modernShop = new FurnitureShop(new ModernFactory());
        assertInstanceOf(ModernChair.class, modernShop.getChair());

        FurnitureShop victorianShop = new FurnitureShop(new VictorianFactory());
        assertInstanceOf(VictorianTable.class, victorianShop.getTable());

        FurnitureShop minimalisticShop = new FurnitureShop(new MinimalisticFactory());
        assertInstanceOf(MinimalisticSofa.class, minimalisticShop.getSofa());
    }

    @Test
    void furnitureShopShouldThrowExceptionWhenFactoryIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new FurnitureShop(null)
        );
        assertEquals("Factory cannot be null", exception.getMessage());
    }
}