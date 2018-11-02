package edu.austral.starship;

import edu.austral.starship.model.components.Component;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.awt.*;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer {

    public void draw(Component component, PGraphics graphics) {
        graphics.pushMatrix();
        graphics.translate(component.getPosition().getX(), component.getPosition().getY());
        graphics.rotate(component.getHeading() - PConstants.PI/2);
        float[][] points =  getPoints(component.getShape());
        graphics.beginShape();
        for (float[] point : points) {
            graphics.vertex(point[0], point[1]);
        }
        graphics.endShape(PConstants.CLOSE);
        graphics.noFill();
        graphics.stroke(255);
        graphics.popMatrix();
    }

    private float[][] getPoints(Shape shape) {
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
