package edu.austral.starship.model.components;

import edu.austral.starship.model.visitors.Visitor;

public class Asteroid extends Component {


    @Override
    public void collisionedWith(Component collisionable) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
