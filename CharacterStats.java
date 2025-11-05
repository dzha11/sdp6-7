package hero;

import strategy.*;

//класс для хранения статистики героя
public class CharacterStats {
    private Difficulty difficulty;
    private int healthBonus;
    private AttackStrategy attackStrategy;

    public CharacterStats(Difficulty difficulty) {
        this.difficulty = difficulty;
        setHealthBonus(difficulty);  // Устанавливаем бонус здоровья в зависимости от сложности
        setNewAttackStrategy(difficulty);  // Устанавливаем стратегию атаки
    }

    private void setNewAttackStrategy(Difficulty difficulty) {
    }

    // получаем бонус к здоровью в зависимости от сложности
    public int getHealthBonus() {
        return healthBonus;
    }

    // устанавливаем бонус здоровья в зависимости от сложности
    private void setHealthBonus(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                this.healthBonus = 50;
                break;
            case MEDIUM:
                this.healthBonus = 100;
                break;
            case HARD:
                this.healthBonus = 150;
                break;
        }
    }

    public AttackStrategy getNewAttackStrategy() {
        switch (difficulty) {
            case EASY:
                return new MeleeAttack();
            case MEDIUM:
                return new RangedAttack();
            case HARD:
                return new MagicAttack();
            default:
                return new MeleeAttack();  // Стратегия по умолчанию
        }
    }

    public void levelUp() {
        if (difficulty == Difficulty.EASY) {
            difficulty = Difficulty.MEDIUM;  // Прокачиваем персонажа
        } else if (difficulty == Difficulty.MEDIUM) {
            difficulty = Difficulty.HARD;
        }
        setHealthBonus(difficulty);  // Обновляем бонус здоровья
    }
}
