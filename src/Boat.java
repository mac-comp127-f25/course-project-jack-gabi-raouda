import java.awt.Color;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;
import java.util.Arrays;

/**
 * Class implementing the boat in the fishing game.
 * 
 * @author Raouda Mamane Bello Boubacar 
 * @author Jack Fang
 * @author Gabi Palladino
 *
 * This class creates a GraphicsGroup to represent the boat, and manages its left and right movement, 
 * based on mouse movement of the user.
 */
public class Boat {
    private GraphicsGroup boatGroup;
    /**
     * Constructs a Boat at the given (x, y) position.
     * Initializes and adds the body, mast and sail to a GraphicsGroup.
     */
    public Boat(double x, double y){
        boatGroup = new GraphicsGroup(x, y);
        Path body = new Path(Arrays.asList(
            new Point(0, 40),  
            new Point(160, 40),
            new Point(140, 80),
            new Point(20, 80)
        ));
        
        body.setFillColor(new Color(255, 0, 0));
        body.setStroked(false);
        boatGroup.add(body); 

        Rectangle mast = new Rectangle(80, -50, 8, 90);
        mast.setFillColor(new Color(120, 80, 40)); 
        mast.setStroked(false);
        boatGroup.add(mast);

        Path sail = new Path(Arrays.asList(
            new Point(88, -50),   
            new Point(88, 10),    
            new Point(140, -20)  
        ));

        sail.setFillColor(new Color(255, 255, 255)); 
        sail.setStroked(true);
        boatGroup.add(sail);
    }
    
     /**
     * Moves the boat horizontally based on the mouse position.
     * Ensures the boat stays within the canvas boundaries.
     */
    public void move(double mouseX, int canvasWidth) {
        double newX = mouseX - boatGroup.getWidth() / 2;

        if (newX < 0) newX = 0;
        if (newX + boatGroup.getWidth() > canvasWidth) {
            newX = canvasWidth - boatGroup.getWidth();
        }

        boatGroup.setX(newX);
    }

    /**
     * Returns the GraphicsObject representing the entire boat.
     * Used to add the boat to the canvas.
     */
    public GraphicsObject getGraphicsObject(){
        return boatGroup;
    }

    /**
     * Returns the current x-coordinate of the boat.
     */
    public double getX(){
        return boatGroup.getX();
    }

    /**
     * Returns the current y-coordinate of the boat.
     */
    public double getY(){
        return boatGroup.getY();
    }
    
}
