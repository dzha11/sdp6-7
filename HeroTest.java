package hero;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import strategy.MeleeAttack;
public class HeroTest {

    // чекаем создания героя
    @Test
    public void testHeroCreation() {
        Hero warrior = new Hero("Warrior", new MeleeAttack(), Difficulty.MEDIUM); // Добавлен параметр Difficulty
        assertEquals("Warrior", warrior.getName());  // Проверяем имя героя
        assertEquals(100, warrior.getHealth());  // Проверяем здоровье
    }

    // ченкаем уменьшения здоровья
    @Test
    public void testHealthDecrease() {
        Hero warrior = new Hero("Warrior", new MeleeAttack(), Difficulty.MEDIUM); // Добавлен параметр Difficulty
        warrior.decreaseHealth(20);
        assertEquals(80, warrior.getHealth());  // Проверяем, что здоровье уменьшилось на 20
    }

    // тесты для создания различных героев с разной сложностью
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
