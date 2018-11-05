package edu.austral.starship.model.visitors;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.model.components.Asteroid;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.components.PowerUp;
import edu.austral.starship.model.components.Spaceship;

public class AsteroidCollisionVisitor extends Visitor {

    public AsteroidCollisionVisitor(CustomGameFramework gameFramework) {
        super(gameFramework);
    }

    @Override
    public void visit(Asteroid asteroid) {}

    @Override
    public void visit(Spaceship spaceship) {
        gameFramework.lifeLost(spaceship);
    }

    @Override
    public void visit(PowerUp powerUp) {
        gameFramework.destroyComponent(powerUp);
    }

    @Override
    public void visit(Bullet bullet) {
        gameFramework.destroyComponent(bullet);
    }
}
