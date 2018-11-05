package edu.austral.starship;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.framework.*;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Key;
import edu.austral.starship.model.components.*;
import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.components.commands.*;
import edu.austral.starship.model.components.guns.BasicGun;
import edu.austral.starship.model.factories.*;
import edu.austral.starship.model.visitors.SpaceshipCollisionVisitor;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CustomGameFramework implements GameFramework {
    private Game game;
    private CollisionEngine<Component> collisionEngine;
    private AsteroidFactory asteroidFactory;
    private Renderer renderer;
    private BulletFactory bulletFactory;
    private AffineTransform affineTransform;
    private List<Component> componentsToBeDestroyed;
    private int amountOfAsteroids;
    private float powerUpTimer;
    private PowerUpFactory powerUpFactory;
    private SpaceshipFactory spaceshipFactory;

    public BulletFactory getBulletFactory() {
        return bulletFactory;
    }

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.setSize(1500, 1000);
        asteroidFactory = new ConcreteAsteroidFactory();
        collisionEngine = new CollisionEngine<>();
        game = new Game();
        renderer = new Renderer();
        affineTransform = new AffineTransform();
        bulletFactory = new ConcreteBulletFactory(this);
        componentsToBeDestroyed = new ArrayList<>();
        amountOfAsteroids = 0;
        powerUpFactory = new ConcretePowerUpFactory();
        powerUpTimer = 50000;
        spaceshipFactory = new ConcreteSpaceshipFactory();



        List<Key> keys = new ArrayList<>();
        Key up = new Key(PGraphics.UP, new ForwardCommand());
        keys.add(up);
        Key down = new Key(PGraphics.DOWN, new BackwardCommand());
        keys.add(down);
        Key left = new Key(PGraphics.LEFT, new RotateLeftCommand());
        keys.add(left);
        Key right = new Key(PGraphics.RIGHT, new RotateRightCommand());
        keys.add(right);
        Key shoot = new Key(32, new FireCommand());
        keys.add(shoot);

        Player player = new Player(keys, "a", createShip());
        game.getPlayers().add(player);

//        List<Key> keys1 = new ArrayList<>();
//        Key up1 = new Key(87, new ForwardCommand());
//        keys1.add(up1);
//        Key down1 = new Key(83, new BackwardCommand());
//        keys1.add(down1);
//        Key left1 = new Key(65, new RotateLeftCommand());
//        keys1.add(left1);
//        Key right1 = new Key(68, new RotateRightCommand());
//        keys1.add(right1);
//
//        Player player1 = new Player(keys1, "a", createShip());
//        game.getPlayers().add(player1);

        for (int i = 0; i < 20; i++) {
            spawnAsteroid();
        }
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        for (Player player : game.getPlayers()) {
            for (Integer i : keySet) {
                player.checkKey(i);
            }
            player.setImmunityFrames(player.getImmunityFrames() - timeSinceLastDraw);
        }
        for (Component component : game.getComponents()) {
            renderer.draw(component, graphics);
            component.move();
            edges(component);
        }
        collisionEngine.checkCollisions(game.getComponents());
        game.getComponents().removeAll(componentsToBeDestroyed);
        componentsToBeDestroyed = new ArrayList<>();
        newShips();
        checkAsteroids();
        checkPowerUps(timeSinceLastDraw);
    }

    @Override
    public void keyPressed(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {}

    public void addBullet(Bullet bullet){
        affineTransform.translate(bullet.getPosition().getX(), bullet.getPosition().getY());
        affineTransform.rotate(bullet.getHeading() - PConstants.PI/2);
        affineTransform.createTransformedShape(bullet.getShape());
        game.getComponents().add(bullet);
    }

    private void spawnAsteroid(){
        Asteroid asteroid = asteroidFactory.create(this);
        affineTransform.translate(asteroid.getPosition().getX(), asteroid.getPosition().getY());
        affineTransform.rotate(asteroid.getHeading() - PConstants.PI/2);
        affineTransform.createTransformedShape(asteroid.getShape());
        game.getComponents().add(asteroid);
        amountOfAsteroids++;
    }

    private void createPlayer(List<Key> keys, String name){
        Player player = new Player(keys, name, createShip());
        game.getPlayers().add(player);
    }

    private Spaceship createShip(){
        Spaceship ship = spaceshipFactory.create(this);

        affineTransform.translate(ship.getPosition().getX(), ship.getPosition().getY());
        affineTransform.rotate(ship.getHeading() - PConstants.PI/2);
        affineTransform.createTransformedShape(ship.getShape());

        game.getComponents().add(ship);
        return ship;
    }

    private void createPowerUp(){
        PowerUp po = powerUpFactory.create(this);
        affineTransform.translate(po.getPosition().getX(), po.getPosition().getY());
        affineTransform.rotate(po.getHeading() - PConstants.PI/2);
        affineTransform.createTransformedShape(po.getShape());

        game.getComponents().add(po);

        powerUpTimer = 50000;
    }

    private void checkPowerUps(float timer) {
        if (powerUpTimer > 0)
            powerUpTimer -= timer;
        else
            createPowerUp();
    }

    public void destroyComponent(Component component) {
        componentsToBeDestroyed.add(component);
    }

    public void lifeLost(Spaceship spaceship) {
        for (Player player : game.getPlayers()) {
            if (player.ownShip(spaceship)) {
                if (player.hasCollided(spaceship))
                    destroyComponent(spaceship);
            }
        }
    }

    private void newShips() {
        for (Player player : game.getPlayers()) {
            if (player.needsAShip())
                player.setSpaceship(createShip());
        }
    }

    private void edges(Component component) {
        if (component.getPosition().getX() > 1500 + component.getSize())
            component.setPosition(Vector2.vector(-component.getSize(), component.getPosition().getY()));
        else if (component.getPosition().getX() < -component.getSize())
            component.setPosition(Vector2.vector(1500 + component.getSize(), component.getPosition().getY()));
        if (component.getPosition().getY() > 1000 + component.getSize())
            component.setPosition(Vector2.vector(component.getPosition().getX(), -component.getSize()));
        else if (component.getPosition().getY() < -component.getSize())
            component.setPosition(Vector2.vector(component.getPosition().getX(), 1000 + component.getSize()));
    }

    public void removeOneAsteroid(){
        amountOfAsteroids--;
    }

    private void checkAsteroids() {
        if (amountOfAsteroids < 10) {
            for (int i = 0; i < 5; i++) {
                spawnAsteroid();
            }
        }
    }
}
