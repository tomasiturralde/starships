package edu.austral.starship.model.visitors;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.model.components.Asteroid;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.components.PowerUp;
import edu.austral.starship.model.components.Spaceship;

public class BulletCollisionVisitor extends Visitor {
    private Bullet bullet;

    public BulletCollisionVisitor(CustomGameFramework gameFramework) {
        super(gameFramework);
    }

    @Override
    public void visit(Asteroid asteroid) {
        int score = (int)(asteroid.getSize() * 2);
        bullet.getObserver().updateScore(score);
        if (asteroid.getLife() > 0)
            asteroid.loseALife();
        else {
            gameFramework.destroyComponent(asteroid);
            gameFramework.removeOneAsteroid();
        }
    }

    @Override
    public void visit(Spaceship spaceship) {
        if (!checkForOwnerShip(spaceship))
            bullet.getObserver().updateScore(400);

        gameFramework.lifeLost(spaceship);
    }

    @Override
    public void visit(PowerUp powerUp) {
        gameFramework.destroyComponent(powerUp);
    }

    @Override
    public void visit(Bullet bullet) {}

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    private boolean checkForOwnerShip(Spaceship spaceship) {
        return bullet.getObserver().getOwner().equals(spaceship.getObserver().getOwner());
    }
}
