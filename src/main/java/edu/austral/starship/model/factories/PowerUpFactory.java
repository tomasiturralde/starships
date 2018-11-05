package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.model.components.PowerUp;

public abstract class PowerUpFactory {
    public abstract PowerUp create(CustomGameFramework gameFramework);
}
