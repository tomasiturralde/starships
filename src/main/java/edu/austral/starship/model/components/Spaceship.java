package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.guns.Gun;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public class Spaceship extends Component {
    private Gun gun;
    private PlayerObserver observer;

    public Spaceship(float angle, float heading, Vector2 position,
                     Shape shape, Visitor assignedVisitor, Gun gun, float size, PlayerObserver observer) {
        super(angle, heading, position, shape, assignedVisitor, size);
        this.gun = gun;
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

    public void shoot(){
        gun.shoot(getPosition(), getHeading(), gun.getBulletSize(), observer);
    }

    public void turn(){
        setHeading(getHeading() + getRotation());
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public Gun getGun() {
        return gun;
    }

    public PlayerObserver getObserver() {
        return observer;
    }

    @Override
    public void move() {}
}
