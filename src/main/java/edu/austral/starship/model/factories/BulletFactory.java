package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;

public abstract class BulletFactory {

    private CustomGameFramework gameFramework;

    public BulletFactory(CustomGameFramework gameFramework) {
        this.gameFramework = gameFramework;
    }

    public abstract void createBullet(Vector2 position, float size, String playerId, float heading);

    public CustomGameFramework getGameFramework() {
        return gameFramework;
    }
}
