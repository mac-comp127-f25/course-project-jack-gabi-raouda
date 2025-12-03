import java.awt.Color;
import java.util.List;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import java.util.ArrayList;


public class FishingGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private static final double WATER_LEVEL = CANVAS_HEIGHT - 150;
    // private FishingLine fishingLine;
    private Boat boat;
    private List<Fish> fish;
    private boolean lineMoving = false;
    private List<Image> fishImages;
    

    public CanvasWindow canvas;
    

    public FishingGame() {
        canvas = new CanvasWindow("Fishing!", CANVAS_WIDTH, CANVAS_HEIGHT);
        drawBackground();

        boat = new Boat(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 50);
        canvas.add(boat.getGraphicObject());
        
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
     * Draws the background including sky, water, seaweed, and other background details.
     */
    private void drawBackground() {
        Rectangle sky = new Rectangle(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
        sky.setFillColor(new Color(135,206,235));
        sky.setStroked(false);
        canvas.add(sky);

        Rectangle water = new Rectangle(0,CANVAS_HEIGHT-650, CANVAS_WIDTH, CANVAS_HEIGHT);
        water.setFillColor(new Color(15,120,190));
        water.setStroked(false);
        canvas.add(water);

        for (int i = 0; i < CANVAS_WIDTH; i += 100) {
            Image seaweed = new Image("other_images/seaweed.png");
            seaweed.setPosition(i, CANVAS_HEIGHT - 150);
            seaweed.setMaxWidth(100);
            canvas.add(seaweed);
        }

        Image bubbles = new Image("other_images/bubbles.png");
        bubbles.setPosition(CANVAS_WIDTH-120, CANVAS_HEIGHT - 620);
        bubbles.setMaxWidth(100);
        canvas.add(bubbles);

        Ellipse sun = new Ellipse(CANVAS_WIDTH-500, 10, 80, 80);
        sun.setFillColor(new Color(255, 255, 191));
        sun.setStroked(false);
        canvas.add(sun);

        Image cloud = new Image("other_images/cloud.png");
        cloud.setPosition(CANVAS_WIDTH-100, 20);
        cloud.setMaxWidth(150);
        canvas.add(cloud);

        Image cloud2 = new Image("other_images/cloud.png");
        cloud2.setPosition(CANVAS_WIDTH-300, -20);
        cloud2.setMaxWidth(200);
        canvas.add(cloud2);
       
        Image cloud3 = new Image("other_images/cloud.png");
        cloud3.setPosition(CANVAS_WIDTH-600, 20);
        cloud3.setMaxWidth(150);
        canvas.add(cloud3);

        // fishImages  = new ArrayList<>();
        // fishImages.add(new Image("res/fish1.png"));
        // fishImages.add(new Image("res/fish2.png"));
        // fishImages.add(new Image("res/fish3.png"));
        // fishImages.add(new Image("res/fish4.png"));
        // fishImages.add(new Image("res/fish5.png"));

    }

      /**
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
