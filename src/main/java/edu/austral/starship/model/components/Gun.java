package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.factories.BulletFactory;

import java.awt.*;

public abstract class Gun {
    private float bulletSize;
    private BulletFactory bulletFactory;

    public Gun(float bulletSize, BulletFactory bulletFactory) {
        this.bulletSize = bulletSize;
        this.bulletFactory = bulletFactory;
    }

    public void shoot(String pId){
        bulletFactory.createBullet(Vector2.vector(0,0),
                0, new Rectangle(), bulletSize, pId, 0);
    }
}
