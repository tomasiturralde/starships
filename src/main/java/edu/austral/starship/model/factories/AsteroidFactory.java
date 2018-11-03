package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.model.components.Asteroid;

public abstract class AsteroidFactory {
    public abstract Asteroid create(CustomGameFramework gameFramework);
}
