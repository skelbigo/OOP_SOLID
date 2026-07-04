import org.example.decorator.buffs.*;
import org.example.factory.HeroFactory;
import org.example.factory.HeroType;
import org.example.models.Hero;
import org.example.observer.GameLogNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RpgEngineTest {

    private HeroFactory factory;
    private GameLogNotifier logger;

    @BeforeEach
    void setUp() {
        factory = new HeroFactory();
        logger = new GameLogNotifier();
    }

    @Test
    void shouldCreateHeroAndLogCombatEvents() {
        Hero sasha = factory.createHero(HeroType.WARRIOR, "Sasha");
        sasha.subscribe(logger);
        assertEquals(25, sasha.attack());

        sasha.takeDamage(30);
        sasha.defeatEnemy();

        List<String> logs = logger.getBattleLogs();
        assertEquals(3, logs.size());
        assertTrue(logs.get(0).contains("[Sasha] took 30 damage! HP left: 120"));
        assertTrue(logs.get(1).contains("VICTORY! [Sasha] defeated an enemy!"));
        assertTrue(logs.get(2).contains("LEVEL UP! [Sasha] reached level 2!"));
    }

    @Test
    void decoratorsShouldModifyHeroStatsDynamically() {
        Hero mage = factory.createHero(HeroType.MAGE, "Merlin");
        mage.subscribe(logger);

        mage = new ShieldBuff(mage);
        mage = new FireDamageBuff(mage);
        mage = new SpeedBoostBuff(mage);

        assertEquals("Swift Merlin", mage.getName());
        assertEquals(55, mage.attack());

        mage.takeDamage(30);

        List<String> logs = logger.getBattleLogs();
        assertEquals(1, logs.size());

        assertTrue(logs.get(0).contains("[Merlin] took 20 damage! HP left: 60"));
    }

    @Test
    void shouldProtectSystemInvariantsAndThrowExceptions() {
        assertThrows(IllegalArgumentException.class, () -> factory.createHero(HeroType.WARRIOR, null));

        Hero validHero = factory.createHero(HeroType.WARRIOR, "Valid");

        assertThrows(IllegalArgumentException.class, () -> validHero.takeDamage(-50));
        assertThrows(IllegalArgumentException.class, () -> validHero.subscribe(null));

        assertThrows(IllegalArgumentException.class, () -> logger.onDamageTaken("", 10, 10));
        assertThrows(IllegalArgumentException.class, () -> logger.onDamageTaken("Name", -10, 10));
        assertThrows(IllegalArgumentException.class, () -> logger.onLevelUp("Name", 0));

        assertThrows(UnsupportedOperationException.class, () -> logger.getBattleLogs().add("Hack"));
    }
}