package edu.austral.starship.model.visitors;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.model.components.Asteroid;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.components.PowerUp;
import edu.austral.starship.model.components.Spaceship;
import edu.austral.starship.model.components.guns.DoubleGun;
import edu.austral.starship.model.components.guns.TripleGun;

import java.util.concurrent.ThreadLocalRandom;

public class PowerUpCollisionVisitor extends Visitor {

    public PowerUpCollisionVisitor(CustomGameFramework gameFramework) {
        super(gameFramework);
    }

    @Override
    public void visit(Asteroid asteroid) {}

    @Override
    public void visit(Spaceship spaceship) {
        int probability = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if (probability <= 70)
            spaceship.setGun(new DoubleGun(2f, gameFramework.getBulletFactory()));
        else if (probability > 71)
            spaceship.setGun(new TripleGun(2f, gameFramework.getBulletFactory()));
    }

    @Override
    public void visit(PowerUp powerUp) {}

    @Override
    public void visit(Bullet bullet) {
        gameFramework.destroyComponent(bullet);
    }
}
