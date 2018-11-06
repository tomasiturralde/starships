package edu.austral.starship;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.framework.*;
import edu.austral.starship.model.components.Key;
import edu.austral.starship.model.components.*;
import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.components.commands.*;
import edu.austral.starship.model.factories.*;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomGameFramework implements GameFramework {
    private Game game;
    private CollisionEngine<Component> collisionEngine;
    private Renderer renderer;
    private List<Component> componentsToBeDestroyed;
    private int amountOfAsteroids;
    private float powerUpTimer;
    private boolean gameOver;
    private Spawner spawner;
    private BulletFactory bulletFactory;

    public BulletFactory getBulletFactory() {
        return bulletFactory;
    }

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.setSize(1500, 980);
        collisionEngine = new CollisionEngine<>();
        game = new Game();
        game.setTimer(50000);
        renderer = new Renderer();
        componentsToBeDestroyed = new ArrayList<>();
        amountOfAsteroids = 0;
        powerUpTimer = 50000;
        spawner = new Spawner();
        bulletFactory = new ConcreteBulletFactory(this);


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

        Player player = new Player(keys, "Player 1");
        player.setSpaceship(createShip(player));
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
        Key shoot1 = new Key(84, new FireCommand());
        keys1.add(shoot1);

        Player player1 = new Player(keys1, "Player 2");
        player1.setSpaceship(createShip(player1));
        game.getPlayers().add(player1);

        for (int i = 0; i < 20; i++) {
            spawnAsteroid();
        }
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        if (!gameOver) {
            renderer.renderText(graphics, game.getPlayers());
            for (Player player : game.getPlayers()) {
                for (Integer i : keySet) {
                    player.checkKey(i);
                }
                player.setImmunityFrames(player.getImmunityFrames() - timeSinceLastDraw);
            }
            for (Component component : game.getComponents()) {
                renderer.draw(component, graphics);
                component.move();
                renderer.edges(component);
            }
            collisionEngine.checkCollisions(game.getComponents());
            game.getComponents().removeAll(componentsToBeDestroyed);
            componentsToBeDestroyed = new ArrayList<>();
            newShips();
            checkAsteroids();
            checkPowerUps(timeSinceLastDraw);
            checkForGameOver(timeSinceLastDraw);
        } else
            gameOver(graphics);
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {}

    public void addBullet(Bullet bullet){
        Bullet newBullet = spawner.addBullet(bullet);
        game.getComponents().add(newBullet);
    }

    private void spawnAsteroid(){
        Asteroid asteroid = spawner.spawnAsteroid(this);
        game.getComponents().add(asteroid);
        amountOfAsteroids++;
    }

//    private void createPlayer(List<Key> keys, String name){
//        Player player = new Player(keys, name);
//        player.setSpaceship(createShip(player));
//        game.getPlayers().add(player);
//    }

    private Spaceship createShip(Player player){
        Spaceship ship = spawner.createShip(this, player);

        game.getComponents().add(ship);
        return ship;
    }

    private void createPowerUp(){
        PowerUp po = spawner.createPowerUp(this);

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
                player.setSpaceship(createShip(player));
        }
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

    private void checkForGameOver(float timer) {
        if (game.getTimer() <= 0)
            gameOver = true;
        else
            game.setTimer(game.getTimer() - timer);
    }

    private void gameOver(PGraphics graphics) {
        Player winner = game.getPlayers().get(0);
        for (int i = 1; i < game.getPlayers().size(); i++) {
            if (winner.getScore() < game.getPlayers().get(i).getScore())
                winner = game.getPlayers().get(i);
        }
        renderer.renderGameOver(graphics, winner);
    }
}
