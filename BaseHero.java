package hero;

import strategy.AttackStrategy;

public class BaseHero extends Hero {
    private String name;
    private int health;
    private AttackStrategy attackStrategy;

    public BaseHero(String name, AttackStrategy attackStrategy, Difficulty difficulty) {
        super(name, attackStrategy, difficulty);
        this.health = 100;  // Можно обновить логику, если необходимо
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void decreaseHealth(int damage) {
        health = Math.max(0, health - damage);
    }

    @Override
    public void attack(Hero target) {
        if (attackStrategy != null) {
            attackStrategy.attack(this, target);
        } else {
            System.out.println(name + " has no attack strategy!");
        }
    }

    @Override
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
}
