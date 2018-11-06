package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public class Bullet extends Component {
    private int velocity;
    private PlayerObserver observer;

    public Bullet(float angle, float heading, Vector2 position, Shape shape,
                  Visitor assignedVisitor, float size, int velocity, PlayerObserver observer) {
        super(angle, heading, position, shape, assignedVisitor, size);
        this.velocity = velocity;
        this.observer = observer;
    }

    @Override
    public void collisionedWith(Component collisionable) {
        collisionable.accept(this.getAssignedVisitor());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void move() {
        setPosition(getPosition().add(Vector2.vectorFromModule(velocity, getHeading())));
    }

    public PlayerObserver getObserver() {
        return observer;
    }
}
