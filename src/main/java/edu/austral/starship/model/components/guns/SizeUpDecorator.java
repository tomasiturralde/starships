package edu.austral.starship.model.components.guns;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.PlayerObserver;
import edu.austral.starship.model.factories.BulletFactory;

public class SizeUpDecorator extends GunDecorator {

    public SizeUpDecorator(BulletFactory bulletFactory) {
        super(bulletFactory);
    }

    @Override
    public void shoot(Vector2 position, float heading, float size, PlayerObserver observer) {
        getGun().shoot(position, heading, getGun().getBulletSize()*5, observer);
    }
}
