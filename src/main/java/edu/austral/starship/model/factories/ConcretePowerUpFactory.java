package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.PowerUp;
import edu.austral.starship.model.components.guns.*;
import edu.austral.starship.model.visitors.PowerUpCollisionVisitor;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConcretePowerUpFactory extends PowerUpFactory{

    @Override
    public PowerUp create(CustomGameFramework gameFramework) {
        int size = 10;
        int[] x = {-size, 0, size, 0};
        int[] y = {0, size, 0, -size};
        Shape shape = new Polygon(x, y, 4);

        int posX = ThreadLocalRandom.current().nextInt(0, 1500 + 1);
        int posY = ThreadLocalRandom.current().nextInt(0, 980 + 1);
        Vector2 position = Vector2.vector(posX, posY);

        PowerUpCollisionVisitor visitor = new PowerUpCollisionVisitor(gameFramework);

        GunDecorator gun = determineGun(gameFramework);

        PowerUp powerUp = new PowerUp(0, 0, position, shape, visitor, gun, size);

        visitor.setPowerUp(powerUp);

        return powerUp;
    }

    private GunDecorator determineGun(CustomGameFramework gameFramework) {
        int probability = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if (probability <= 20)
            return new BackGun(gameFramework.getBulletFactory());
        else if (probability <= 60)
            return new DoubleGun(gameFramework.getBulletFactory());
        else
            return new SizeUpDecorator(gameFramework.getBulletFactory());
    }
}
