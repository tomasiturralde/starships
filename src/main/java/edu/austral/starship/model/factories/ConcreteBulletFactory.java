package edu.austral.starship.model.factories;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Bullet;
import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.visitors.Visitor;
import edu.austral.starship.view.Observer;
import edu.austral.starship.view.VisualBullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConcreteBulletFactory extends BulletFactory {

    public ConcreteBulletFactory(CustomGameFramework gameFramework) {
        super(gameFramework);
    }

    public void createBullet(Visitor visitor, Vector2 position, float angle,
                             Shape shape, float size, String playerId, float heading){
        List<Observer<Component>> observers = new ArrayList<>();
        observers.add(new VisualBullet());
        Bullet bullet = new Bullet(angle, heading, position, shape, observers, visitor, playerId, size);
        getGameFramework().addBullet(bullet);
    }
}
