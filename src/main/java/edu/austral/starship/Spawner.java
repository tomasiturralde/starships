package edu.austral.starship;

import edu.austral.starship.model.components.*;
import edu.austral.starship.model.factories.*;
import processing.core.PConstants;

import java.awt.geom.AffineTransform;

class Spawner {
    private AffineTransform affineTransform;
    private AsteroidFactory asteroidFactory;
    private PowerUpFactory powerUpFactory;
    private SpaceshipFactory spaceshipFactory;

    Spawner() {
        affineTransform = new AffineTransform();
        powerUpFactory = new ConcretePowerUpFactory();
        spaceshipFactory = new ConcreteSpaceshipFactory();
        asteroidFactory = new ConcreteAsteroidFactory();
    }

    Asteroid spawnAsteroid(CustomGameFramework gameFramework){
        Asteroid asteroid = asteroidFactory.create(gameFramework);

        applyTransform(asteroid);

        return asteroid;
    }

    Spaceship createShip(CustomGameFramework gameFramework, Player player){
        Spaceship ship = spaceshipFactory.create(gameFramework, player);

        applyTransform(ship);

        return ship;
    }

    PowerUp createPowerUp(CustomGameFramework gameFramework){
        PowerUp powerUp = powerUpFactory.create(gameFramework);
        applyTransform(powerUp);

        return powerUp;
    }

    Bullet addBullet(Bullet bullet){
        applyTransform(bullet);
        return bullet;
    }

    private void applyTransform(Component component) {
        affineTransform.translate(component.getPosition().getX(), component.getPosition().getY());
        affineTransform.rotate(component.getHeading() - PConstants.PI/2);
        affineTransform.createTransformedShape(component.getShape());
    }
}
