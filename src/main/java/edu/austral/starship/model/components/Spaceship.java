package edu.austral.starship.model.components;

import edu.austral.starship.model.visitors.Visitor;

public class Spaceship extends Component {
    Gun gun;

    @Override
    public void collisionedWith(Component collisionable) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void shoot(String pId){
        gun.shoot(pId);
    }
}
