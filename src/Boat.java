import java.awt.Color;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Arc;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.KeyboardEventHandler;
import java.util.Arrays;

public class Boat {
    private GraphicsGroup boatGroup;

    public Boat(double x, double y){
        boatGroup = new GraphicsGroup(x, y);
        Path body = new Path(Arrays.asList(
            new Point(0, 40),  
            new Point(160, 40),
            new Point(140, 80),
            new Point(20, 80)
        ));
        body.setFillColor(new Color(255, 0, 0));
        body.setStrocked(false);
        boatGroup.add(body);
        

    

    
    public void mvoeLeft(){
        boatGroup.moveBy(-5,0);
    }
    public void moveRight(){
        boatGroup.moveBy(5,0);
    }
    public GraphicsObject getGraphicsObject(){
        return boatGroup;
    }
    public double getX(){
        return boatGroup.getX();
    }
    public double getY(){
        return boatGroup.getY();
    }







    

    // public Boat(params) {
    //     //code to create the boat graphic object at the given position
    // }
}
