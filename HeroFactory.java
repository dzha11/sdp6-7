package hero;

import strategy.*;

public class HeroFactory {

    public static Hero createHero(String type, Difficulty difficulty) {
        Hero hero;
        // Создаем героя с соответствующей стратегией и уровнем сложности
        switch (type.toLowerCase()) {
            case "warrior":
                hero = new Hero("Warrior", new MeleeAttack(), difficulty);  // Войн с атакой ближнего боя
                break;
            case "mage":
                hero = new Hero("Mage", new MagicAttack(), difficulty);  // Маг с магической атакой
                break;
            case "archer":
                hero = new Hero("Archer", new RangedAttack(), difficulty);  // Лучник с атакой на дальнем расстоянии
                break;
            case "firemage":
                hero = new FireMage(difficulty);  // Создание FireMage с учетом сложности
                break;
            case "tankwarrior":
                hero = new TankWarrior(difficulty);  // Создание TankWarrior с учетом сложности
                break;
            case "finalboss":
                hero = new FinalBoss(difficulty);  // Создание FinalBoss с учетом сложности
                break;
            default:
                throw new IllegalArgumentException("Unknown hero type: " + type);  // Если тип не найден
        }


        hero = new MagicAttackDecorator(hero, difficulty);
        return hero;
    }
    public static Hero createEnemy(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return new Hero("Mage", new MagicAttack(), Difficulty.EASY);
            case MEDIUM:
                return new Hero("FireMage", new FireAttack(), Difficulty.MEDIUM);
            case HARD:
                return new Hero("FinalBoss", new MagicAttack(), Difficulty.HARD);
            default:
                return new Hero("Mage", new MagicAttack(), Difficulty.MEDIUM);
        }
    }
}
