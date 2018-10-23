package edu.austral.starship.view;

import java.awt.*;
import java.util.Observer;

public abstract class VisualComponent implements Observer {
    String id;

    public abstract void draw(Shape shape);

    public abstract void destroy();
}
