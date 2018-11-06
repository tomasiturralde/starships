package edu.austral.starship.model.components.guns;

import edu.austral.starship.model.factories.BulletFactory;

public abstract class GunDecorator extends Gun {
    private Gun gun;

    GunDecorator(BulletFactory bulletFactory) {
        super(bulletFactory);
    }

    Gun getGun() {
        return gun;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    @Override
    public float getBulletSize() {
        return gun.getBulletSize();
    }
}
