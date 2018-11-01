package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import edu.austral.starship.view.Observer;

import java.awt.*;
import java.util.List;

public class Spaceship extends Component {
    private Gun gun;

    public Spaceship(float angle, float heading, Vector2 position, Shape shape,
                     List<Observer<Component>> observers, Visitor assignedVisitor, Gun gun) {
        super(angle, heading, position, shape, observers, assignedVisitor);
        this.gun = gun;
    }

    @Override
    public void collisionedWith(Component collisionable) {
        collisionable.accept(this.getAssignedVisitor());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void shoot(String pId){
        gun.shoot(pId);
    }

    public void turn(){
        setHeading(getHeading() + getRotation());
    }
}
