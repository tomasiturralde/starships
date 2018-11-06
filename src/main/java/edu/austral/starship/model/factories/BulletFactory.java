package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.PlayerObserver;

public abstract class BulletFactory {

    private CustomGameFramework gameFramework;

    public BulletFactory(CustomGameFramework gameFramework) {
        this.gameFramework = gameFramework;
    }

    public abstract void create(Vector2 position, float size, float heading, PlayerObserver observer);

    public CustomGameFramework getGameFramework() {
        return gameFramework;
    }
}
