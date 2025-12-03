

import java.awt.Color;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;

public class Boat {
    // public Boat(params) {
    //     //code to create the boat graphic object at the given position
    // }

    private GraphicsGroup group;
    private double centerX;
    private double centerY;
    private double width = 80;
    private double height = 40;

    public Boat(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        group = new GraphicsGroup();

        // Hull
        Ellipse hull = new Ellipse(centerX - width/2, centerY - height/2, width, height);
        hull.setFillColor(Color.BLUE);
        hull.setStrokeColor(Color.BLACK);
        group.add(hull);

        Rectangle mask = new Rectangle(centerX - width/2, centerY - height/2, width, height/2);
        mask.setFillColor(Color.WHITE); 
        mask.setStroked(false);
        group.add(mask);

        // Mast 
        Rectangle mast = new Rectangle(centerX - 2, centerY - height/2 - 60, 4, 60);
        mast.setFillColor(Color.DARK_GRAY);
        mast.setStroked(false);
        group.add(mast);

        // Sail

    }

    public GraphicsGroup getGraphicObject() {
        return group;
    }

    //Move boat horizontally 
    public void moveBy(double dx) {
        group.moveBy(dx, 0);
        centerX += dx;
    }

    public double getCenterX() { return centerX; }
    public double getCenterY() { return centerY; }

    }

