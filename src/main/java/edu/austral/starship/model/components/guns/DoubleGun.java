package edu.austral.starship.model.components.guns;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.PlayerObserver;
import edu.austral.starship.model.factories.BulletFactory;

public class DoubleGun extends GunDecorator {
    public DoubleGun(BulletFactory bulletFactory) {
        super(bulletFactory);
    }

    @Override
    public void shoot(Vector2 position, float heading, float size, PlayerObserver observer) {
        getGun().shoot(position, (float)(heading - Math.PI/8), getGun().getBulletSize(), observer);
        getGun().shoot(position, (float)(heading + Math.PI/8), getGun().getBulletSize(), observer);
    }


}
