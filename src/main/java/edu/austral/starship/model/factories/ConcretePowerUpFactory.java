package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.PowerUp;
import edu.austral.starship.model.components.guns.DoubleGun;
import edu.austral.starship.model.components.guns.Gun;
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
        int posY = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
        Vector2 position = Vector2.vector(posX, posY);

        PowerUpCollisionVisitor visitor = new PowerUpCollisionVisitor(gameFramework);

        Gun gun = new DoubleGun(2f, gameFramework.getBulletFactory());

        return new PowerUp(0, 0, position, shape, visitor, gun, size);
    }
}
