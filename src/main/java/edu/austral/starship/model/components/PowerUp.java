package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import edu.austral.starship.view.Observer;

import java.awt.*;
import java.util.List;

public class PowerUp extends Component {
    private GunDecorator gunDecorator;

    public PowerUp(float angle, float heading, Vector2 position, Shape shape,
                   List<Observer> observers, Visitor assignedVisitor, GunDecorator gunDecorator) {
        super(angle, heading, position, shape, observers, assignedVisitor);
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
}
