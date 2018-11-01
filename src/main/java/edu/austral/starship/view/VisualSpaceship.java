package edu.austral.starship.view;

import processing.core.PConstants;
import edu.austral.starship.model.components.Component;
import processing.core.PGraphics;

public class VisualSpaceship extends VisualComponent {


    @Override
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
//        ts.turn();
    }

}
