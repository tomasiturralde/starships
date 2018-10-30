package edu.austral.starship.model.components;

import edu.austral.starship.model.factories.BulletFactory;

public abstract class GunDecorator extends Gun{
    private Gun gun;

    public GunDecorator(float bulletSize, BulletFactory bulletFactory, Gun gun) {
        super(bulletSize, bulletFactory);
        this.gun = gun;
    }

    @Override
    public void shoot(String pId) {

    }
}
