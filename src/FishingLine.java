import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

/**
 * Class implementing the fishing line in the fishing game.
 * 
 * @author Raouda Mamane Bello Boubacar 
 * @author Jack Fang
 * @author Gabi Palladino
 *
 * This class creates a Rectangle to create the fishing line, and manages its up and down movement, 
 * based on the user pressing the space bar.
 */
public class FishingLine {
    private Rectangle line;
    private double x;
    private double y;

    private double length = 0;
    private boolean dropping = false;
    private boolean goingUp = false;

    private static final double LINE_WIDTH = 3;
    private static final double MAX_LENGTH = 450;
    private static final double SPEED = 4;

    public FishingLine(CanvasWindow canvas, double startX, double startY) {
        this.x = startX;
        this.y = startY;
        line = new Rectangle(x, y, LINE_WIDTH, 0);
        line.setFillColor(java.awt.Color.BLACK);
        canvas.add(line);
    }

    /**
     * Updates the position of the fishing line making it always attached to the bottom of the boat.
     */
    public void updatePosition(double boatX, double boatBottomY) {
        this.x = boatX;
        this.y = boatBottomY;
        line.setPosition(x, y);
    }

    /**
     * Sets dropping to true and goingUp to false, making the line drop.
     */
    public void dropLine() { 
        dropping = true; 
        goingUp = false; 
    }

    /**
     * Sets goingUp to true and dropping to false, making the line go up.
     */
    public void pullUp(){ 
    goingUp = true;
    dropping = false;
    }
    
    /**
     * Updates the length of the fishing line based the speed and whether it is dropping or going up.
     */
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

    /**
     * Redraws the fishing line based on its current length.
     */
    private void redrawLine() {
        line.setSize(LINE_WIDTH, length);
        line.setPosition(x, y);
    }

    /**
     * Returns the rectangle that is the fishing line.
     */
    public Rectangle getShape() {
        return line;
    }

    /**
     * Returns the x coordinate of the hook (bottom center of the fishing line).
     */
    public double getHookX() {
    return x + LINE_WIDTH / 2;
    }

    /**
     * Returns the y coordinate of the hook (bottom of the fishing line).
     */
    public double getHookY() {
        return y + length;
    }

    /**
     * Returns boolean of whether the line is going up.
     */
    public boolean isGoingUp() {
        return goingUp;
    }

    /**
     * Returns boolean of whether the line is dropping.
     */
    public boolean dropping() {
        return dropping;
    }

}