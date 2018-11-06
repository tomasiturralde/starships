package edu.austral.starship;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.components.Player;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.awt.*;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Renderer {

    void draw(Component component, PGraphics graphics) {
        graphics.pushMatrix();
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

    void edges(Component component) {
        if (component.getPosition().getX() > 1500 + component.getSize())
            component.setPosition(Vector2.vector(-component.getSize(), component.getPosition().getY()));
        else if (component.getPosition().getX() < -component.getSize())
            component.setPosition(Vector2.vector(1500 + component.getSize(), component.getPosition().getY()));
        if (component.getPosition().getY() > 980 + component.getSize())
            component.setPosition(Vector2.vector(component.getPosition().getX(), -component.getSize()));
        else if (component.getPosition().getY() < -component.getSize())
            component.setPosition(Vector2.vector(component.getPosition().getX(), 980 + component.getSize()));
    }

    void renderText(PGraphics graphics, List<Player> players) {
        int x = 0;
        for (Player player : players) {
            graphics.pushMatrix();
            graphics.textSize(22);
            graphics.text(player.getName(), x, 870);
            graphics.popMatrix();
            x += 150;
        }
        x = 0;
        for (Player player : players) {
            graphics.pushMatrix();
            graphics.text("Score: " + player.getScore(), x, 920);
            graphics.popMatrix();
            x += 150;
        }
        x = 0;
        for (Player player : players) {
            graphics.pushMatrix();
            graphics.text("Lives: " + player.getLives(), x, 970);
            graphics.popMatrix();
            x += 150;
        }
    }

    void renderGameOver(PGraphics graphics, Player winner){
        graphics.pushMatrix();
        graphics.textSize(60);
        graphics.text("Game Over", 560, 470);
        graphics.text("Winner: " + winner.getName(), 500, 560);
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
