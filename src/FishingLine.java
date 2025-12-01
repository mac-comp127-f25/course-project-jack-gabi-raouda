import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class FishingLine {
    private Rectangle line;
    private double startX;
    private double startY;

    private boolean dropping = false;
    private boolean goingUp = false;

    private static final double LineWidth = 3;
    private static final double MaxLength = 450;
    private static final double Speed = 3;

    public FishingLine(CanvasWindow canvas, double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
        line = new Rectangle(startX, startY, LineWidth, 0);
        line.setFillColor(java.awt.Color.BLACK);
        canvas.add(line);
    }
    public void dropLine() { 
        dropping = true; 
        goingUp = false; 
    }

    public void goingUp() { 
    goingUp = true;
    dropping = false;
}
 public void update() {
        if (dropping) {
            if (line.getHeight() < MaxLength) {
                line.setHeight(line.getHeight() + Speed);
            } else {
                dropping = false;
            }
        } else if (goingUp) {
            if (line.getHeight() > 0) {
                line.setHeight(line.getHeight() - Speed);
            } else {
                goingUp = false;
            }
        }
    }
}