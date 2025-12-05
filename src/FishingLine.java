import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class FishingLine {
    private CanvasWindow canvas;
    private Rectangle line;
    private double x;
    private double y;

    private double length = 0;
    private boolean dropping = false;
    private boolean goingUp = false;

    private static final double LINE_WIDTH = 3;
    private static final double MAX_LENGTH = 450;
    private static final double SPEED = 3;

    public FishingLine(CanvasWindow canvas, double startX, double startY) {
        this.canvas = canvas;
        this.x = startX;
        this.y = startY;
        line = new Rectangle(x, y, LINE_WIDTH, 0);
        line.setFillColor(java.awt.Color.BLACK);
        canvas.add(line);
    }

    // Attach line top to boat whenver it moves
     public void updatePosition(double boatX, double boatY) {
        this.x = boatX;
        this.y = boatY;

        redrawLine();
    }

    public void dropLine() { 
        dropping = true; 
        goingUp = false; 
    }

    public void goingUp() { 
    goingUp = true;
    dropping = false;
}

   // animate the line
    public void update() {
        if (dropping && length < MAX_LENGTH) {
            length += SPEED;
        } else if (dropping && length >= MAX_LENGTH) {
            dropping = false;
            goingUp = true;
        }

        if (goingUp && length > 0) {
            length -= SPEED;
        }

        if (length < 0) length = 0;

        redrawLine();
    }

    // remove old rectangle and draw new one with updated length
    private void redrawLine() {
        canvas.remove(line);
        line = new Rectangle(x, y, LINE_WIDTH, length);
        line.setFillColor(java.awt.Color.BLACK);
        canvas.add(line);
    }

    public Rectangle getShape() {
        return line;
    }
    }