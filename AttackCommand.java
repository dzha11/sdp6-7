package command;

import hero.Hero;

public class AttackCommand implements Command {
    private Hero attacker;  // игрок
    private Hero target;    // враг

    // констр, выбиреаем атакующего и мишень
    public AttackCommand(Hero attacker, Hero target) {
        this.attacker = attacker;
        this.target = target;
    }

    // метод execute, который выполняет атаку
    @Override
    public void execute() {
        attacker.attack(target);
    }
}
