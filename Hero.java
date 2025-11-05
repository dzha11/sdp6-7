package hero;

import strategy.AttackStrategy;

public class Hero {
    private String name;
    private int health;
    private AttackStrategy attackStrategy;
    private Difficulty difficulty;

    public Hero(String name, AttackStrategy attackStrategy, Difficulty difficulty) {
        this.name = name;
        this.attackStrategy = attackStrategy;
        this.difficulty = difficulty;
        this.health = difficulty.getHealth();  // Устанавливаем здоровье в зависимости от сложности
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int damage) {
        health = Math.max(0, health - damage);
    }

    public void attack(Hero target) {
        if (attackStrategy != null) {
            attackStrategy.attack(this, target);
        } else {
            System.out.println(name + " has no attack strategy!");
        }
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
