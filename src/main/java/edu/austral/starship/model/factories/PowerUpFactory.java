package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.model.components.PowerUp;

public abstract class PowerUpFactory {
    private CustomGameFramework gameFramework;

    PowerUpFactory(CustomGameFramework gameFramework){
        this.gameFramework = gameFramework;
    }

    public abstract PowerUp create();

    public CustomGameFramework getGameFramework() {
        return gameFramework;
    }
}
