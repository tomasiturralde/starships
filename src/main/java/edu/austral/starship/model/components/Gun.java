package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.factories.BulletFactory;
import edu.austral.starship.model.visitors.BulletCollisionVisitor;

import java.awt.*;

public abstract class Gun {
    private float bulletSize;
    private BulletFactory bulletFactory;

    public Gun(float bulletSize, BulletFactory bulletFactory) {
        this.bulletSize = bulletSize;
        this.bulletFactory = bulletFactory;
    }

    public void shoot(String pId){
        bulletFactory.createBullet(new BulletCollisionVisitor(), Vector2.vector(0,0),
                Vector2.vector(0,0), new Rectangle(), bulletSize, pId);
    }
}
