import java.awt.Color;
import java.util.ArrayList;
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
    private static final double FISH_Y_BOUND = 170;
    private FishingLine fishingLine;
    private Boat boat;
    private List<Fish> fishes = Fish.generateFish();
    private Fish caughtFish = null;
    private boolean lineMoving = false;
    

    public CanvasWindow canvas;
    

    public FishingGame() {
        canvas = new CanvasWindow("Fishing!", CANVAS_WIDTH, CANVAS_HEIGHT);
        drawBackground();
        boat = new Boat(CANVAS_WIDTH / 2 , WATER_LEVEL - 570);
        canvas.add(boat.getGraphicsObject());
        
        
        canvas.onMouseMove(event -> {
            if (fishingLine.dropping()) {
                return; 
            } else {
            double mouseX = event.getPosition().getX();
            boat.move(mouseX, CANVAS_WIDTH); 
        }
        });

        // Create fishing line starting at the center top of the boat
        fishingLine = new FishingLine(canvas, boat.getX() + boat.getGraphicsObject().getWidth() / 2, boat.getY() + 40);

        // Animate the line and boat together
        canvas.animate(() -> {
            fishingLine.updatePosition(boat.getX() + boat.getGraphicsObject().getWidth() / 2,boat.getY() + 80);
            fishingLine.update();

        checkIfFishHit();
        removeFishAtSurface();

        for (Fish f : fishes) {
            if (f == caughtFish) {
                f.followLine(
                    fishingLine.getHookX(),
                    fishingLine.getHookY()
                );
            } else {
                f.moveFish(CANVAS_WIDTH, FISH_Y_BOUND);
            }
        }
        });


        //Drop the line on SPACE key
        canvas.onKeyDown(event -> {
            if (event.getKey().toString().equals("SPACE")) {
                fishingLine.dropLine();
            }
        });
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

    }

      /**
     * Resets the canvas by removing everything, redrawing new fish, and resetting the paddle and ball to default position.
     */
    public void resetGame() {
        // canvas.removeAll();
        generateAllFish();
        canvas.draw();
    }


    /**
     * Generates the fish list and adds them to the canvas.
     */
    public void generateAllFish() {
        for (Fish f : fishes) {
            f.addToCanvas(canvas);
            System.out.println(f);
        }
    }

    /**
     * Checks if the line has hit any fish and pulls them up to the water surface if so.
     */
    private void checkIfFishHit() {
            if (caughtFish != null) return; 

            for (Fish f : fishes) {
            if (!f.isCaught() && !fishingLine.isGoingUp()) {
            double fishX = f.getImage().getCenter().getX();
            double fishY = f.getImage().getCenter().getY();

            double hookX = fishingLine.getHookX();
            double hookY = fishingLine.getHookY();

            double distance = Math.hypot(fishX - hookX, fishY - hookY);

            if (distance < 30) {
                f.catchFish();
                caughtFish = f;       // store the ONE active fish
                fishingLine.pullUp();
                break;
            }
        }
    }
    }
    
    /**
     * Helper method to remove the fish from the canvas when it reaches the surface of the water.
     */
    private void removeFishAtSurface(){
        List<Fish> toRemove = new ArrayList<>();

    for (Fish f : fishes) {
        if (f.isCaught()) {
            if (f.getImage().getCenter().getY() <= FISH_Y_BOUND) {
                canvas.remove(f.getImage());
                toRemove.add(f);
            }
        }
    }
    
    fishes.removeAll(toRemove);
    if (!toRemove.isEmpty()) {
    caughtFish = null;  
        }
    }

}
