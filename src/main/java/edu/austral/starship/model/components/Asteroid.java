package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;
import java.util.List;
import java.util.Observer;

public class Asteroid extends Component {
    private int life;
    private float size;

    public Asteroid(Vector2 movement, Vector2 position, Shape shape,
                    List<Observer> observers, Visitor assignedVisitor, int life, float size) {
        super(movement, position, shape, observers, assignedVisitor);
        this.life = life;
        this.size = size;
    }

    @Override
    public void collisionedWith(Component collisionable) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
