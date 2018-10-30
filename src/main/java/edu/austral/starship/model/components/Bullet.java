package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;

import java.awt.*;
import java.util.List;
import java.util.Observer;

public class Bullet extends Component {
    private String playerId;
    private float size;

    public Bullet(Vector2 movement, Vector2 position, Shape shape, List<Observer> observers,
                  Visitor assignedVisitor, String playerId, float size) {
        super(movement, position, shape, observers, assignedVisitor);
        this.playerId = playerId;
        this.size = size;
    }

    @Override
    public void collisionedWith(Component collisionable) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getPlayerId() {
        return playerId;
    }

    public float getSize() {
        return size;
    }
}
