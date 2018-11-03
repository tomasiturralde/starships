package edu.austral.starship;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.framework.*;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Key;
import edu.austral.starship.model.components.*;
import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.components.commands.*;
import edu.austral.starship.model.factories.*;
import edu.austral.starship.model.visitors.BulletCollisionVisitor;
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
    private AffineTransform affineTransform = new AffineTransform();
    private BulletCollisionVisitor bulletVisitor = new BulletCollisionVisitor(this);
    private List<Component> componentsToBeDestroyed = new ArrayList<>();

    public BulletCollisionVisitor getBulletVisitor() {
        return bulletVisitor;
    }

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.setSize(1500, 1500);
        asteroidFactory = new ConcreteAsteroidFactory();
        collisionEngine = new CollisionEngine<>();
        game = new Game();
        renderer = new Renderer();


        List<Key> keys = new ArrayList<>();
        Key up = new Key(PGraphics.UP, new ForwardCommand());
        keys.add(up);
        Key down = new Key(PGraphics.DOWN, new BackwardCommand());
        keys.add(down);
        Key left = new Key(PGraphics.LEFT, new RotateLeftCommand());
        keys.add(left);
        Key right = new Key(PGraphics.RIGHT, new RotateRightCommand());
        keys.add(right);

        Player player = new Player(keys, "a", createShip());
        game.getPlayers().add(player);


        List<Key> keys1 = new ArrayList<>();
        Key up1 = new Key(87, new ForwardCommand());
        keys1.add(up1);
        Key down1 = new Key(83, new BackwardCommand());
        keys1.add(down1);
        Key left1 = new Key(65, new RotateLeftCommand());
        keys1.add(left1);
        Key right1 = new Key(68, new RotateRightCommand());
        keys1.add(right1);

        Player player1 = new Player(keys1, "a", createShip());
        game.getPlayers().add(player1);

        for (int i = 0; i < 10; i++) {
            spawnAsteroid();
        }
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        for (Player player : game.getPlayers()) {
            for (Integer i : keySet) {
                player.checkKey(i);
            }
            player.setInmunityFrames(player.getInmunityFrames() - timeSinceLastDraw);
        }
        for (Component component : game.getComponents()) {
            renderer.draw(component, graphics);
        }
        collisionEngine.checkCollisions(game.getComponents());
        game.getComponents().removeAll(componentsToBeDestroyed);
        componentsToBeDestroyed = new ArrayList<>();
    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    public void addBullet(Bullet bullet){
        game.getComponents().add(bullet);
    }

    public void spawnAsteroid(){
        Asteroid asteroid = asteroidFactory.create(this);
        affineTransform.translate(asteroid.getPosition().getX(), asteroid.getPosition().getY());
        affineTransform.rotate(asteroid.getHeading() - PConstants.PI/2);
        affineTransform.createTransformedShape(asteroid.getShape());
        game.getComponents().add(asteroid);
    }

    private void createPlayer(List<Key> keys, String name){
        Player player = new Player(keys, name, createShip());
        game.getPlayers().add(player);
    }

    private Spaceship createShip(){
        int[] x = {-25,0,25};
        int[] y = {-25, 25, -25};
        int posX = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
        int posY = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
        Spaceship ship = new Spaceship(0, 0, Vector2.vector(posX, posY), new Polygon(x, y, 3),
                new SpaceshipCollisionVisitor(this), new BasicGun(1.5f, new ConcreteBulletFactory(this)));

        affineTransform.translate(ship.getPosition().getX(), ship.getPosition().getY());
        affineTransform.rotate(ship.getHeading() - PConstants.PI/2);
        affineTransform.createTransformedShape(ship.getShape());

        game.getComponents().add(ship);
        return ship;
    }

    public void destroyComponent(Component component) {
        componentsToBeDestroyed.add(component);
    }

    public void lifeLost(Spaceship spaceship) {
        for (Player player : game.getPlayers()) {
            if (player.ownShip(spaceship)) {
                player.checkCollision(spaceship);
                destroyComponent(spaceship);
            }
        }
    }
}
