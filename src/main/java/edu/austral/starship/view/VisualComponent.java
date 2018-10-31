package edu.austral.starship.view;

import processing.core.PGraphics;

import edu.austral.starship.model.components.Component;

import java.awt.*;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class VisualComponent implements Observer<Component> {
    String id;
    PGraphics graphics;

    public abstract void draw(Component component);

    static float[][] getPoints(Shape shape) {
        List<float[]> pointList = new ArrayList<>();
        float[] coords = new float[6];
        int numSubPaths = 0;
        for (PathIterator pi = shape.getPathIterator(null, 0); !pi.isDone(); pi.next()) {
            switch (pi.currentSegment(coords)) {
                case PathIterator.SEG_MOVETO:
                    pointList.add(Arrays.copyOf(coords, 2));
                    ++ numSubPaths;
                    break;
                case PathIterator.SEG_LINETO:
                    pointList.add(Arrays.copyOf(coords, 2));
                    break;
                case PathIterator.SEG_CLOSE:
                    if (numSubPaths > 1) {
                        throw new IllegalArgumentException("Path contains multiple subpaths");
                    }
                    return pointList.toArray(new float[pointList.size()][]);
                default:
                    throw new IllegalArgumentException("Path contains curves");
            }
        }
        throw new IllegalArgumentException("Unclosed path");
    }

}
