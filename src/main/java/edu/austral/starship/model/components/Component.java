package edu.austral.starship.model.components;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Component extends Observable implements Collisionable<Component> {
    String id;
    Vector2 movement;
    Vector2 position;
    private Shape shape;
    List<Observer> observers;
    Visitor assignedVisitor;

    Component(){

    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public abstract void accept(Visitor visitor);
}
