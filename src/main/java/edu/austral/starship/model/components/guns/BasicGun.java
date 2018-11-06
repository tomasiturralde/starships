package edu.austral.starship.model.components.guns;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.PlayerObserver;
import edu.austral.starship.model.factories.BulletFactory;

public class BasicGun extends Gun {

    public BasicGun(BulletFactory bulletFactory) {
        super(bulletFactory);
        setBulletSize(1f);
    }

    @Override
    public void shoot(Vector2 position, float heading, float size, PlayerObserver observer) {
        getBulletFactory().create(position, size, heading, observer);
    }
}
