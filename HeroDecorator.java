package hero;

import strategy.AttackStrategy;

public class HeroDecorator extends Hero {
    protected Hero decoratedHero;

    // rонстр с передачей аргументов в родительский класс
    public HeroDecorator(Hero decoratedHero) {
        super(decoratedHero.getName(), decoratedHero.getAttackStrategy(), decoratedHero.getDifficulty());  // передаем имя, стратегию атаки и сложность
        this.decoratedHero = decoratedHero; 
    }

    @Override
    public void attack(Hero target) {
        decoratedHero.attack(target); 
    }

    @Override
    public String getName() {
        return decoratedHero.getName();  
    }

    @Override
    public int getHealth() {
        return decoratedHero.getHealth(); 
    }

    @Override
    public void decreaseHealth(int damage) {
        decoratedHero.decreaseHealth(damage);  
    }

    @Override
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        decoratedHero.setAttackStrategy(attackStrategy); 
    }

    @Override
    public AttackStrategy getAttackStrategy() {
        return decoratedHero.getAttackStrategy(); 
    }

    @Override
    public Difficulty getDifficulty() {
        return decoratedHero.getDifficulty();  
    }
}
