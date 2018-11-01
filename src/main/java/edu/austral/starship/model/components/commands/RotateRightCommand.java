package edu.austral.starship.model.components.commands;

import edu.austral.starship.model.components.Spaceship;

public class RotateRightCommand extends Command {
    @Override
    public void execute(Spaceship spaceship) {
        spaceship.setRotation(0.1f);
        spaceship.turn();
    }
}
