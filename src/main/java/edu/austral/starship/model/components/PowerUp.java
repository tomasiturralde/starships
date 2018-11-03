package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public class PowerUp extends Component {
    private GunDecorator gunDecorator;

    public PowerUp(float angle, float heading, Vector2 position,
                   Shape shape, Visitor assignedVisitor, GunDecorator gunDecorator, float size) {
        super(angle, heading, position, shape, assignedVisitor, size);
        this.gunDecorator = gunDecorator;
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
    public void move() {}

    public GunDecorator getGunDecorator() {
        return gunDecorator;
    }
}
