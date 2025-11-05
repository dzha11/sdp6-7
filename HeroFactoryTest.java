package hero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroFactoryTest {

    @Test
    public void testCreateWarrior() {
        Hero warrior = HeroFactory.createHero("Warrior", Difficulty.MEDIUM);

        // чекаем что имя и здоровье героя соответствует
        assertEquals("Warrior", warrior.getName());
        assertEquals(100, warrior.getHealth());
    }

    @Test
    public void testCreateMage() {
        Hero mage = HeroFactory.createHero("Mage", Difficulty.HARD);

        // чекаем что имя и здоровье героя соответствует
        assertEquals("Mage", mage.getName());
        assertEquals(150, mage.getHealth());  // Здоровье для сложного уровня
    }

    @Test
    public void testCreateArcher() {
        Hero archer = HeroFactory.createHero("Archer", Difficulty.EASY);

        // чекаем что имя и здоровье героя соответствует
        assertEquals("Archer", archer.getName());
        assertEquals(50, archer.getHealth());  // Здоровье для легкого уровня
    }
}
