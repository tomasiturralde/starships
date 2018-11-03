package edu.austral.starship.model.visitors;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.model.components.*;

public abstract class Visitor {
    CustomGameFramework gameFramework;

    public Visitor(CustomGameFramework gameFramework) {
        this.gameFramework = gameFramework;
    }

    public abstract void visit(Asteroid asteroid);

    public abstract void visit(Spaceship spaceship);

    public abstract void visit(PowerUp powerUp);

    public abstract void visit(Bullet bullet);
}
