package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;
import java.util.List;
import java.util.Observer;

public class Spaceship extends Component {
    private Gun gun;

    public Spaceship(Vector2 movement, Vector2 position, Shape shape,
                     List<Observer> observers, Visitor assignedVisitor, Gun gun) {
        super(movement, position, shape, observers, assignedVisitor);
        this.gun = gun;
    }

    @Override
    public void collisionedWith(Component collisionable) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void shoot(String pId){
        gun.shoot(pId);
    }
}
