package edu.austral.starship.model.components;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Component implements Collisionable<Component> {
    private String id;
    private float rotation;
    private float heading;
    private Vector2 position;
    private final Shape shape;
    private Visitor assignedVisitor;
    private float size;

    Component(float rotation, float heading, Vector2 position,
                     Shape shape, Visitor assignedVisitor, float size) {
        this.rotation = rotation;
        this.heading = heading;
        this.position = position;
        this.shape = shape;
        this.assignedVisitor = assignedVisitor;
        shape.getPathIterator(null ,0);
        this.size = size;
    }

    @Override
    public Shape getShape() {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.rotate(heading - Math.PI/2);
        return at.createTransformedShape(shape);
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

    public Visitor getAssignedVisitor() {
        return assignedVisitor;
    }

    public void setAssignedVisitor(Visitor assignedVisitor) {
        this.assignedVisitor = assignedVisitor;
    }

    public abstract void move();

    public float getSize() {
        return size;
    }
}
