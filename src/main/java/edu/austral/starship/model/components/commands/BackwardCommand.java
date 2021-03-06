package edu.austral.starship.model.components.commands;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Spaceship;

public class BackwardCommand implements Command {
    @Override
    public void execute(Spaceship spaceship) {
        spaceship.setPosition(spaceship.getPosition().add(Vector2.vectorFromModule(-5, spaceship.getHeading())));
    }
}
