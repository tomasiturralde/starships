package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.visitors.BulletCollisionVisitor;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;

public class ConcreteBulletFactory extends BulletFactory {

    public ConcreteBulletFactory(CustomGameFramework gameFramework) {
        super(gameFramework);
    }

    public void createBullet(Vector2 position, float size, String playerId, float heading){

        Vector2 offsetPosition = position.add(Vector2.vectorFromModule(30, heading));
        int aSize = (int)size;
        int[] x = {-aSize,0,aSize};
        int[] y = {-aSize, aSize, -aSize};

        Shape shape = new Polygon(x, y, 3);

        int velocity = 7;

        Visitor visitor = new BulletCollisionVisitor(getGameFramework());

        Bullet bullet = new Bullet(0, heading, offsetPosition, shape,
                visitor, playerId, size, velocity);
        getGameFramework().addBullet(bullet);
    }
}
