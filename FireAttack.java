package strategy;

import hero.Hero;

public class FireAttack implements AttackStrategy {

    @Override
    public int attack(Hero attacker, Hero target) {
        //логика атаки
        System.out.println(attacker.getName() + " launches a powerful fireball at " + target.getName() + "!");
        return 30; // урон огнём
    }
}
