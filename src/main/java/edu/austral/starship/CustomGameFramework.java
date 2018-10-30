package edu.austral.starship;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.model.components.Bullet;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.Set;

public class CustomGameFramework implements GameFramework {
    private int moveX = 0;
    private int moveY = 0;
    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(500, 500);
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        graphics.beginDraw();
        //a --> eje x, b --> eje y, c --> width, d --> height
        graphics.rect(moveX,moveY,50,50);
        graphics.fill(135);
        graphics.endDraw();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (PGraphics.UP == event.getKeyCode())
            moveY -= 20;
        else if (PGraphics.DOWN == event.getKeyCode())
            moveY += 20;
        else if (PGraphics.LEFT == event.getKeyCode())
            moveX -= 20;
        else if (PGraphics.RIGHT == event.getKeyCode())
            moveX += 20;
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    public void addBullet(Bullet bullet){

    }
}
