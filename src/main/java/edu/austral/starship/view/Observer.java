package edu.austral.starship.view;

import processing.core.PGraphics;

public interface Observer<T> {

    void update(T component, PGraphics graphics);
}
