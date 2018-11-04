package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.guns.Gun;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public class Spaceship extends Component {
    private Gun gun;

    public Spaceship(float angle, float heading, Vector2 position,
                     Shape shape, Visitor assignedVisitor, Gun gun, float size) {
        super(angle, heading, position, shape, assignedVisitor, size);
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
        gun.shoot(pId, getPosition(), getHeading());
    }

    public void turn(){
        setHeading(getHeading() + getRotation());
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    @Override
    public void move() {}
}
