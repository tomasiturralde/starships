package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.model.components.Spaceship;

public abstract class SpaceshipFactory {
    public abstract Spaceship create(CustomGameFramework gameFramework);
}
