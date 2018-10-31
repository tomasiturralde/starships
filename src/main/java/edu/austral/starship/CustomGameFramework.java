package edu.austral.starship;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.components.TestComponent;
import edu.austral.starship.model.factories.AsteroidFactory;
import edu.austral.starship.model.factories.ConcreteAsteroidFactory;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import java.util.Set;

public class CustomGameFramework implements GameFramework {
    private Game game;
    private AsteroidFactory asteroidFactory;
    TestComponent ts = new TestComponent();


    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.setSize(1500, 1500);
        asteroidFactory = new ConcreteAsteroidFactory();
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        System.out.println(event.getKeyCode());
        System.out.println(PGraphics.UP);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        ts.setRotation(0);
    }

    public void addBullet(Bullet bullet){
        game.getComponents().add(bullet);
    }

    public void spawnAsteroid(){
        game.getComponents().add(asteroidFactory.create());
    }
}
