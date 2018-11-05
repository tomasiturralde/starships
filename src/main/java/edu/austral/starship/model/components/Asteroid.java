package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public class Asteroid extends Component {
    private int life;
    private int velocity;

    public Asteroid(float angle, float heading, Vector2 position, Shape shape,
                    Visitor assignedVisitor, int life, float size, int velocity) {
        super(angle, heading, position, shape, assignedVisitor, size);
        this.life = life;
        this.velocity = velocity;
    }

    @Override
    public void collisionedWith(Component collisionable) {
        collisionable.accept(this.getAssignedVisitor());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getLife() {
        return life;
    }

    public void loseALife() {
        life--;
    }

    @Override
    public void move() {
        setPosition(getPosition().add(Vector2.vectorFromModule(velocity, getHeading())));
    }
}
