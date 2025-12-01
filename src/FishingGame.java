import java.awt.Color;
import java.util.List;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;


public class FishingGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private static final double WATER_LEVEL = CANVAS_HEIGHT - 150;
    private FishingLine fishingLine;
    private Boat boat;
    private List<Fish> fish;
    private boolean lineMoving = false;
    private List<Image> fishImages;
    

    public CanvasWindow canvas;
    

    public FishingGame() {
        canvas = new CanvasWindow("Fishing!", CANVAS_WIDTH, CANVAS_HEIGHT);
        drawBackground();
        // boat = new boat(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 50);
        // canvas.add(boat.getGraphicObject());
        
        //code that handles keyboard input to move the boat left and right
    }

    /**
     * Main method that calls the run method.
     */
    public static void main(String[] args){
        FishingGame game = new FishingGame();
        game.run();
    }

    /**
     * Runs the game.
     */
    public void run() {
        resetGame(); 
    }

    /**
<<<<<<< Updated upstream
     * Resets the canvas by removing everything, redrawing new fish, and resetting the boat to default position
    private void drawBackground() {
        Rectangle sky = new Rectangle(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
        sky.setFillColor(new Color(135,206,235));
        sky.setStroked(false);
        canvas.add(sky);

        Rectangle water = new Rectangle(0,CANVAS_HEIGHT-150, CANVAS_WIDTH, CANVAS_HEIGHT);
        sky.setFillColor(new Color(15,120,190));
        sky.setStroked(false);
        canvas.add(water);
    }




      /**
=======
>>>>>>> Stashed changes
     * Resets the canvas by removing everything, redrawing new fish, and resetting the paddle and ball to default position.
     */
    public void resetGame() {
        // canvas.removeAll();
        // generateAllFish();
        // canvas.add(boat.getGraphicObject());
        canvas.draw();
    }

    /**
     * Creates the fishingLine object attached to the boat.
     */   
    private void createFishingLine() {
        //make fishing line ? maybe this can be called in the boat class instead
    }


    /**
     * Generates the fish list and adds them to the canvas.
     */
    public void generateAllFish() {

        generateAllFish();
        // fishes = Fish.createBricks();
        // for (Fish fish : fishes) {
        //     canvas.add(fish.getGraphicObject());
        // }
    }

    /**
     * Checks if the line has hit any fish and pulls them up to the water surface if so.
     */
    private void checkIfFishHit() {
        //code
    }
    
    /**
     * Helper method to remove the fish from the canvas when it reaches the surface of the water.
     */
    private void removeFishAtSurface(GraphicsObject caughtFish) {
        //code
    }

}
