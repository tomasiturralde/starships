package edu.austral.starship.model.components;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public class Bullet extends Component {
    private String playerId;
    private float size;

    public Bullet(float angle, float heading, Vector2 position, Shape shape, Visitor assignedVisitor, String playerId, float size) {
        super(angle, heading, position, shape, assignedVisitor);
        this.playerId = playerId;
        this.size = size;
    }

    @Override
    public void collisionedWith(Component collisionable) {
        collisionable.accept(this.getAssignedVisitor());
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
