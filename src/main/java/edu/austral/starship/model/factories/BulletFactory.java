package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;

public abstract class BulletFactory {

    private CustomGameFramework gameFramework;

    public void createBullet(Visitor visitor, Vector2 position, Vector2 movement, Shape shape, float size, String playerId){
        Bullet bullet = new Bullet();
//        gameFramework.addBullet(bullet);
    }
}
