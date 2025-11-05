package strategy;

import hero.Hero;
import hero.Difficulty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MeleeAttackTest {

    @Test
    public void testAttack() {
        // создаем героя и врага с соответствующими стратегиями атаки
        Hero attacker = new Hero("Warrior", new MeleeAttack(), Difficulty.MEDIUM);
        Hero target = new Hero("Enemy", new MeleeAttack(), Difficulty.MEDIUM);

        // выполняем атаку
        attacker.attack(target);

        // чекаем что здоровье врага уменьшилось на 20
        assertEquals(80, target.getHealth());
    }
}
