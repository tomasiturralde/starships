package edu.austral.starship.model.components;

import edu.austral.starship.model.factories.BulletFactory;

public class BasicGun extends Gun {

    public BasicGun(float bulletSize, BulletFactory bulletFactory) {
        super(bulletSize, bulletFactory);
    }
}
