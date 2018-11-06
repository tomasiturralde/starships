package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Player;
import edu.austral.starship.model.components.Spaceship;
import edu.austral.starship.model.components.guns.BasicGun;
import edu.austral.starship.model.components.guns.Gun;
import edu.austral.starship.model.components.PlayerObserver;
import edu.austral.starship.model.visitors.SpaceshipCollisionVisitor;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConcreteSpaceshipFactory extends SpaceshipFactory {

    @Override
    public Spaceship create(CustomGameFramework gameFramework, Player player) {
        int size = 25;
        int[] x = {-size + 5,0,size-5};
        int[] y = {-size, size, -size};
        Shape shape = new Polygon(x, y, 3);

        int posX = ThreadLocalRandom.current().nextInt(0, 1400 + 1);
        int posY = ThreadLocalRandom.current().nextInt(0, 980 + 1);
        Vector2 position = Vector2.vector(posX, posY);

        SpaceshipCollisionVisitor visitor = new SpaceshipCollisionVisitor(gameFramework);

        Gun gun = new BasicGun(new ConcreteBulletFactory(gameFramework));

        PlayerObserver observer = new PlayerObserver(player);

        return new Spaceship(0, 0, position, shape, visitor, gun, size, observer);
    }
}
