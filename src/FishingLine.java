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

    public void drop() {}
}
