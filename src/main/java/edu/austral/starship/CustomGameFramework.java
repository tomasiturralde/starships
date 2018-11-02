package edu.austral.starship;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.framework.*;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Key;
import edu.austral.starship.model.components.*;
import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.components.commands.*;
import edu.austral.starship.model.factories.*;
import edu.austral.starship.model.visitors.SpaceshipCollisionVisitor;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomGameFramework implements GameFramework {
    private Game game;
    private CollisionEngine<Component> collisionEngine;
    private AsteroidFactory asteroidFactory;
    private Renderer renderer;

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.setSize(1500, 1500);
        asteroidFactory = new ConcreteAsteroidFactory();
        collisionEngine = new CollisionEngine<>();
        game = new Game();
        renderer = new Renderer();
        int[] x = {-25,0,25};
        int[] y = {-25, 25, -25};
        Spaceship ship = new Spaceship(0, 0, Vector2.vector(100, 100), new Polygon(x, y, 3),
                new SpaceshipCollisionVisitor(), new BasicGun(1.5f, new ConcreteBulletFactory(this)));

        game.getComponents().add(ship);

        List<Key> keys = new ArrayList<>();
        Key up = new Key(PGraphics.UP, new ForwardCommand());
        keys.add(up);
        Key down = new Key(PGraphics.DOWN, new BackwardCommand());
        keys.add(down);
        Key left = new Key(PGraphics.LEFT, new RotateLeftCommand());
        keys.add(left);
        Key right = new Key(PGraphics.RIGHT, new RotateRightCommand());
        keys.add(right);

        Player player = new Player(keys, "a", ship);
        game.getPlayers().add(player);

        Spaceship ship1 = new Spaceship(0, 0, Vector2.vector(200, 200), new Polygon(x, y, 3),
                new SpaceshipCollisionVisitor(), new BasicGun(1.5f, new ConcreteBulletFactory(this)));

        game.getComponents().add(ship1);

        List<Key> keys1 = new ArrayList<>();
        Key up1 = new Key(87, new ForwardCommand());
        keys1.add(up1);
        Key down1 = new Key(83, new BackwardCommand());
        keys1.add(down1);
        Key left1 = new Key(65, new RotateLeftCommand());
        keys1.add(left1);
        Key right1 = new Key(68, new RotateRightCommand());
        keys1.add(right1);

        Player player1 = new Player(keys1, "a", ship1);
        game.getPlayers().add(player1);
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        for (Integer i : keySet) {
            for (Player player : game.getPlayers()) {
                player.checkKey(i);
            }
        }
        for (Component component : game.getComponents()) {
            renderer.draw(component, graphics);
        }
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
        game.getComponents().add(asteroidFactory.create());
    }

    private void createPlayer(){

    }
}
