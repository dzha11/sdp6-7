package strategy;

import hero.Hero;

//реализация магической атаки.наносит сильный урон, но требует концентрации.

public class MagicAttack implements AttackStrategy {

    @Override
    public int attack(Hero attacker, Hero target) {
        int damage = 25;
        target.decreaseHealth(damage);
        System.out.println(attacker.getName() + " casts a powerful spell on " + target.getName() +
                " for " + damage + " damage (Magic Attack)!");
        return damage;
    }
}
