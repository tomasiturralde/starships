package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.visitors.Visitor;
import java.awt.*;

public abstract class BulletFactory {

    private CustomGameFramework gameFramework;

    public BulletFactory(CustomGameFramework gameFramework) {
        this.gameFramework = gameFramework;
    }

    public abstract void createBullet(Visitor visitor, Vector2 position, float angle,
                             Shape shape, float size, String playerId, float heading);

    public CustomGameFramework getGameFramework() {
        return gameFramework;
    }
}
