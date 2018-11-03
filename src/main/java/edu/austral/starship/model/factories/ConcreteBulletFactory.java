package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Bullet;

import java.awt.*;
import java.awt.geom.Line2D;

public class ConcreteBulletFactory extends BulletFactory {

    public ConcreteBulletFactory(CustomGameFramework gameFramework) {
        super(gameFramework);
    }

    public void createBullet(Vector2 position, float size, String playerId, float heading){

        Shape shape = new Line2D.Float();

        int velocity = 5;

        Bullet bullet = new Bullet(0, heading, position, shape,
                getGameFramework().getBulletVisitor(), playerId, size, velocity);
        getGameFramework().addBullet(bullet);
    }
}
