package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.factories.BulletFactory;

public abstract class Gun {
    private float bulletSize;
    private BulletFactory bulletFactory;

    Gun(float bulletSize, BulletFactory bulletFactory) {
        this.bulletSize = bulletSize;
        this.bulletFactory = bulletFactory;
    }

    public void shoot(String pId, Vector2 position, float heading){
        bulletFactory.createBullet(position, bulletSize, pId, heading);
    }
}
