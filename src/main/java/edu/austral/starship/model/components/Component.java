package edu.austral.starship.model.components;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import edu.austral.starship.view.Observer;

import java.awt.*;
import java.util.List;
import java.util.Observable;

public abstract class Component extends Observable implements Collisionable<Component> {
    private String id;
    private float rotation;
    private float heading;
    private Vector2 position;
    private Shape shape;
    private List<Observer> observers;
    private Visitor assignedVisitor;

    public Component(float rotation, float heading, Vector2 position, Shape shape, List<Observer> observers, Visitor assignedVisitor) {
        this.rotation = rotation;
        this.heading = heading;
        this.position = position;
        this.shape = shape;
        this.observers = observers;
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

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public Visitor getAssignedVisitor() {
        return assignedVisitor;
    }

    public void setAssignedVisitor(Visitor assignedVisitor) {
        this.assignedVisitor = assignedVisitor;
    }
}
