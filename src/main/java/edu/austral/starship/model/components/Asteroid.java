package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public class Asteroid extends Component {
    private int life;
    private float size;

    public Asteroid(float angle, float heading, Vector2 position, Shape shape,
                    Visitor assignedVisitor, int life, float size) {
        super(angle, heading, position, shape, assignedVisitor);
        this.life = life;
        this.size = size;
    }

    @Override
    public void collisionedWith(Component collisionable) {
        collisionable.accept(this.getAssignedVisitor());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
