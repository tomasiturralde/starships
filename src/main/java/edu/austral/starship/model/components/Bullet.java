package edu.austral.starship.model.components;

import edu.austral.starship.model.visitors.Visitor;

public class Bullet extends Component {
    String playerId;
    float size;

    @Override
    public void collisionedWith(Component collisionable) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
