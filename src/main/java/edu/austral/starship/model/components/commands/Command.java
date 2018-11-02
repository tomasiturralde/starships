package edu.austral.starship.model.components.commands;

import edu.austral.starship.model.components.Spaceship;

public interface Command {
    public void execute(Spaceship spaceship);
}
