package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;
import java.util.List;
import java.util.Observer;

public class PowerUp extends Component {
    private GunDecorator gunDecorator;

    public PowerUp(Vector2 movement, Vector2 position, Shape shape,
                   List<Observer> observers, Visitor assignedVisitor, GunDecorator gunDecorator) {
        super(movement, position, shape, observers, assignedVisitor);
        this.gunDecorator = gunDecorator;
    }

    @Override
    public void collisionedWith(Component collisionable) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
