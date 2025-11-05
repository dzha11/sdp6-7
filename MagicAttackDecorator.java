package hero;

import strategy.*;

public class MagicAttackDecorator extends HeroDecorator {

    // констр с передачей Hero и Difficulty
    public MagicAttackDecorator(Hero decoratedHero, Difficulty difficulty) {
        super(decoratedHero);
    }

    @Override
    public void attack(Hero target) {
        super.attack(target);
        System.out.println(getName() + " casts a magical spell on " + target.getName());
    }
}
