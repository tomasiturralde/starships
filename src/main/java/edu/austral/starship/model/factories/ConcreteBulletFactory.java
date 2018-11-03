package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Bullet;
import java.awt.*;

public class ConcreteBulletFactory extends BulletFactory {

    public ConcreteBulletFactory(CustomGameFramework gameFramework) {
        super(gameFramework);
    }

    public void createBullet(Vector2 position, float angle,
                             Shape shape, float size, String playerId, float heading){

        Bullet bullet = new Bullet(angle, heading, position, shape, getGameFramework().getBulletVisitor(), playerId, size);
        getGameFramework().addBullet(bullet);
    }
}
