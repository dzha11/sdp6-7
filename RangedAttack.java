package strategy;

import hero.Hero;

//реализация дальнобойной атаки.наносит чуть меньше урона, но более стабильна.

public class RangedAttack implements AttackStrategy {

    @Override
    public int attack(Hero attacker, Hero target) {
        int damage = 15;
        target.decreaseHealth(damage);
        System.out.println(attacker.getName() + " shoots an arrow at " + target.getName() +
                " for " + damage + " damage (Ranged Attack)!");
        return damage;
    }
}
