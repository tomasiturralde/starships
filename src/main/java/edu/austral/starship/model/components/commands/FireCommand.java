package edu.austral.starship.model.components.commands;

import edu.austral.starship.model.components.Spaceship;

public class FireCommand extends Command {

    @Override
    public void execute(Spaceship spaceship) {
        spaceship.shoot("CORREGIR");
    }
}
