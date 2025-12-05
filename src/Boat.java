import java.awt.Color;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;
import java.util.Arrays;

public class Boat {
    private GraphicsGroup boatGroup;

    public Boat(double x, double y){
        // Body
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

        // Mast
        Rectangle mast = new Rectangle(80, -50, 8, 90);
        mast.setFillColor(new Color(120, 80, 40)); 
        mast.setStroked(false);
        boatGroup.add(mast);


        // Sail
        Path sail = new Path(Arrays.asList(
            new Point(88, -50),   
            new Point(88, 10),    
            new Point(140, -20)  
        ));

        sail.setFillColor(new Color(255, 255, 255)); 
        sail.setStroked(true);
        boatGroup.add(sail);
    }

    
    public void move(double mouseX, int canvasWidth) {
        double newX = mouseX - boatGroup.getWidth() / 2;

        // Keep boat inside the screen
        if (newX < 0) newX = 0;
        if (newX + boatGroup.getWidth() > canvasWidth) {
            newX = canvasWidth - boatGroup.getWidth();
        }

        boatGroup.setX(newX);
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
    
}
