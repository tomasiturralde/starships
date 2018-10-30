package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.visitors.Visitor;
import edu.austral.starship.view.VisualBullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public abstract class BulletFactory {

    private CustomGameFramework gameFramework;

    public BulletFactory(CustomGameFramework gameFramework) {
        this.gameFramework = gameFramework;
    }

    public void createBullet(Visitor visitor, Vector2 position, Vector2 movement,
                             Shape shape, float size, String playerId){
        List<Observer> observers = new ArrayList<>();
        observers.add(new VisualBullet());
        Bullet bullet = new Bullet(movement, position, shape, observers, visitor, playerId, size);
        gameFramework.addBullet(bullet);
    }
}
