package edu.austral.starship.model.components;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Component extends Observable implements Collisionable<Component> {
    private String id;
    private Vector2 movement;
    private Vector2 position;
    private Shape shape;
    private List<Observer> observers;
    private Visitor assignedVisitor;

    public Component(Vector2 movement, Vector2 position, Shape shape, List<Observer> observers, Visitor assignedVisitor) {
        this.movement = movement;
        this.position = position;
        this.shape = shape;
        this.observers = observers;
        this.assignedVisitor = assignedVisitor;
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

    public Vector2 getMovement() {
        return movement;
    }

    public void setMovement(Vector2 movement) {
        this.movement = movement;
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
