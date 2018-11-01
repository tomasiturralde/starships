package edu.austral.starship.model.components.commands;

import edu.austral.starship.model.components.Spaceship;

public abstract class Command {
    public abstract void execute(Spaceship spaceship);
}
