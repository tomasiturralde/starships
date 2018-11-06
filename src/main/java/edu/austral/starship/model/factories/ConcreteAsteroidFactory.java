package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Asteroid;
import edu.austral.starship.model.visitors.AsteroidCollisionVisitor;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConcreteAsteroidFactory extends AsteroidFactory{

    @Override
    public Asteroid create(CustomGameFramework gameFramework) {
        int posX = ThreadLocalRandom.current().nextInt(0, 1500 + 1);
        int posY = ThreadLocalRandom.current().nextInt(0, 980 + 1);
        Vector2 position = Vector2.vector(posX, posY);

        float size = ThreadLocalRandom.current().nextInt(15, 70 + 1);

        int lives =  1 + (int)(size/20);

        AsteroidCollisionVisitor visitor = new AsteroidCollisionVisitor(gameFramework);

        Shape shape = shapeCreator(size);

        float heading = (float) ThreadLocalRandom.current().nextDouble(0, Math.PI*2);

        int velocity = ThreadLocalRandom.current().nextInt(-3, 3 + 1);
        if (velocity == 0)
            velocity = 1;


        return new Asteroid(0, heading, position, shape, visitor, lives, size, velocity);
    }

    private Shape shapeCreator(float size){
        int amountOfVertices = ThreadLocalRandom.current().nextInt(5, 15 + 1);
        int[] xPoints = new int[amountOfVertices];
        int[] yPoints = new int[amountOfVertices];
        int offset;

        for (int i = 0; i < amountOfVertices; i++) {
            offset = ThreadLocalRandom.current().nextInt(-10, 20 + 1);
            float angle = map(i, amountOfVertices);
            xPoints[i] = (int)((size + offset) * Math.cos(angle));
            yPoints[i] = (int)((size + offset)* Math.sin(angle));
        }

        return new Polygon(xPoints, yPoints, amountOfVertices);
    }

    private float map(float value, float stop1) {
        return (float) 0 + ((float) 6.2831855 - (float) 0) * ((value - (float) 0) / (stop1 - (float) 0));
    }
}
