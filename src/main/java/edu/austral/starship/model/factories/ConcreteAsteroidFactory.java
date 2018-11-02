package edu.austral.starship.model.factories;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Asteroid;
import edu.austral.starship.model.visitors.AsteroidCollisionVIsitor;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.ThreadLocalRandom;

public class ConcreteAsteroidFactory extends AsteroidFactory{

    @Override
    public Asteroid create() {
        float posX = ThreadLocalRandom.current().nextInt(0, 500 + 1);
        float posY = ThreadLocalRandom.current().nextInt(0, 500 + 1);
        Vector2 position = Vector2.vector(posX, posY);

        float size = ThreadLocalRandom.current().nextInt(0, 70 + 1);

        //Calc lives
        int lives = 1;

        Visitor visitor = new AsteroidCollisionVIsitor();

        Shape shape = new Ellipse2D.Float(posX, posY, size, size);

        return new Asteroid(0, 0, position, shape, visitor, lives, size);
    }
}
