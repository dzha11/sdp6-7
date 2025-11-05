package strategy;

import hero.Hero;

//интерфейс стратегии атаки.определяет общий метод для всех типов атак.

public interface AttackStrategy {
    int attack(Hero attacker, Hero target);
}
