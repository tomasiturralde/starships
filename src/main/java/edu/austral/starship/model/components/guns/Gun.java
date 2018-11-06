package edu.austral.starship.model.components.guns;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.PlayerObserver;
import edu.austral.starship.model.factories.BulletFactory;

public abstract class Gun {
    private float bulletSize;
    private BulletFactory bulletFactory;

    Gun(BulletFactory bulletFactory) {
        this.bulletFactory = bulletFactory;
    }

    public abstract void shoot(Vector2 position, float heading, float size, PlayerObserver observer);

    BulletFactory getBulletFactory() {
        return bulletFactory;
    }

    public float getBulletSize() {
        return bulletSize;
    }

    public void setBulletSize(float bulletSize) {
        this.bulletSize = bulletSize;
    }
}
