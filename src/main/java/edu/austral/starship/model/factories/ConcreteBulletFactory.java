package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public class ConcreteBulletFactory extends BulletFactory {

    public ConcreteBulletFactory(CustomGameFramework gameFramework) {
        super(gameFramework);
    }

    public void createBullet(Visitor visitor, Vector2 position, float angle,
                             Shape shape, float size, String playerId, float heading){

        Bullet bullet = new Bullet(angle, heading, position, shape, visitor, playerId, size);
        getGameFramework().addBullet(bullet);
    }
}
