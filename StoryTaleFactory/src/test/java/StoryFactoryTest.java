import org.example.StoryTeller;
import org.example.factory.StoryFactory;
import org.example.factory.fairytale.ClassicFairyTaleFactory;
import org.example.factory.modern.ModernAdventureFactory;
import org.example.factory.superhero.SuperheroStoryFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoryFactoryTest {
    @Test
    void classicFairyTaleFactoryShouldCreateConsistentStory() {
        StoryFactory factory = new ClassicFairyTaleFactory();

        assertEquals("Prince or Princess", factory.createHero().describe());
        assertEquals("The Evil Sorcerer", factory.createVillain().describe());
        assertEquals("A Terrible Curse", factory.createConflict().describe());
        assertEquals("Happy Ending", factory.createEnding().describe());
    }

    @Test
    void modernAdventureFactoryShouldCreateConsistentStory() {
        StoryFactory factory = new ModernAdventureFactory();

        assertEquals("An ordinary person", factory.createHero().describe());
        assertEquals("A corrupt system or a problem in the city", factory.createVillain().describe());
        assertEquals("A Difficult Personal Choice", factory.createConflict().describe());
        assertEquals("Open Final", factory.createEnding().describe());
    }

    @Test
    void superheroStoryFactoryShouldCreateConsistentStory() {
        StoryFactory factory = new SuperheroStoryFactory();

        assertEquals("Superhero", factory.createHero().describe());
        assertEquals("Supervillain", factory.createVillain().describe());
        assertEquals("Global Crisis", factory.createConflict().describe());
        assertEquals("Victory at the cost of great sacrifice", factory.createEnding().describe());
    }

    @Test
    void storyTellerShouldWorkWithAnyFactory() {
        StoryTeller fairyTaleTeller = new StoryTeller(new ClassicFairyTaleFactory());
        assertNotNull(fairyTaleTeller.getHero());
        assertTrue(fairyTaleTeller.toString().contains("Prince or Princess"));

        StoryTeller heroTeller = new StoryTeller(new SuperheroStoryFactory());
        assertTrue(heroTeller.toString().contains("Global Crisis"));
    }

    @Test
    void storyTellerShouldThrowExceptionWhenFactoryIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StoryTeller(null)
        );
        assertEquals("Story factory cannot be null", exception.getMessage());
    }
}
