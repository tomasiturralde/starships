package edu.austral.starship.model.components;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public abstract class Component implements Collisionable<Component> {
    private String id;
    private float rotation;
    private float heading;
    private Vector2 position;
    private Shape shape;
    private Visitor assignedVisitor;

    public Component(float rotation, float heading, Vector2 position, Shape shape, Visitor assignedVisitor) {
        this.rotation = rotation;
        this.heading = heading;
        this.position = position;
        this.shape = shape;
        this.assignedVisitor = assignedVisitor;
        shape.getPathIterator(null ,0);
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public abstract void accept(Visitor visitor);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getHeading() {
        return heading;
    }

    public void setHeading(float heading) {
        this.heading = heading;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Visitor getAssignedVisitor() {
        return assignedVisitor;
    }

    public void setAssignedVisitor(Visitor assignedVisitor) {
        this.assignedVisitor = assignedVisitor;
    }
}
