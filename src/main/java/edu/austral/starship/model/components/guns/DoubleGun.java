package edu.austral.starship.model.components.guns;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.factories.BulletFactory;

public class DoubleGun extends Gun{
    public DoubleGun(float bulletSize, BulletFactory bulletFactory) {
        super(bulletSize, bulletFactory);
    }

    @Override
    public void shoot(String pId, Vector2 position, float heading) {
        getBulletFactory().createBullet(position, getBulletSize(), pId, (float)(heading + Math.PI/8));
        getBulletFactory().createBullet(position, getBulletSize(), pId, (float)(heading - Math.PI/8));
    }
}
