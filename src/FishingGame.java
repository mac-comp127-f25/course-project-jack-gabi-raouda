import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

import java.util.ArrayList;
import edu.macalester.graphics.GraphicsText;


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
    private final int TOTAL_FISH = 15;
    private GraphicsText scoreLabel;
    private int score = 0;
    private boolean gameOver = false;
    private boolean gameWin = false;
    private GraphicsText messageText;
    private Button restartButton;
    private Button exitButton;
    private GraphicsText rulesText;

    public CanvasWindow canvas;
    

    public FishingGame() {
        canvas = new CanvasWindow("Fishing!", CANVAS_WIDTH, CANVAS_HEIGHT);
        drawBackground();
        scoreLabel = new GraphicsText("Score: 0", 20, 30);
        scoreLabel.setFontSize(24);           
        scoreLabel.setFillColor(Color.BLACK);
        canvas.add(scoreLabel);
        rulesText = new GraphicsText(
            "Rules:\n" +
            "Press SPACE to fish\n" +
            "Shark = Game Over\n" +
            "Whale or Squid = +100\n" +
            "Tuna = +70\n" +
            "Other Fish = +40",
            CANVAS_WIDTH - 170,40
        );
        rulesText.setFontSize(14);
        rulesText.setFillColor(Color.BLACK);
        canvas.add(rulesText);

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

        fishingLine = new FishingLine(canvas, boat.getX() + boat.getGraphicsObject().getWidth() / 2, boat.getY() + 40);
        
        canvas.animate(() -> {
            if (gameOver || gameWin) return;
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
        generateAllFish();
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
     * Generates the fish list and adds them to the canvas.
     */
    public void generateAllFish() {
        for (Fish f : fishes) {
            f.addToCanvas(canvas);
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
                caughtFish = f;
                fishingLine.pullUp();
                break;
            }
        }
    }
    }
    
    /**
     * Removes the fish from the canvas when it reaches the surface of the water.
     */
    private void removeFishAtSurface() {
        List<Fish> toRemove = new ArrayList<>();
        for (Fish f : fishes) {
            if (f.isCaught()) {
                if (f.getImage().getCenter().getY() <= FISH_Y_BOUND) {

                    if (f.isDeadly()) {
                        setGameOver();
                        return;
                    }
                    score += f.getValue();
                    scoreLabel.setText("Score: " + score);

                    canvas.remove(f.getImage());
                    toRemove.add(f);
                    checkWin();
                }
            }
        }
        fishes.removeAll(toRemove);
        if (!toRemove.isEmpty()) {
            caughtFish = null;  
            }
    }

    /**
     * Checks if player won by catching all non-deadly fish (non-sharks).
     */
    private void checkWin() {
        boolean allCaught = true;
        for (Fish f : fishes) {
            if (!f.isDeadly() && !f.isCaught()) {
                allCaught = false;
                break;
            }
        }
        if (allCaught) {
            gameWin = true;

            GraphicsText winText = new GraphicsText("YOU WIN!", 200, 400);
            winText.setFontSize(40);
            winText.setFillColor(Color.YELLOW);
            canvas.add(winText);
            showButtons();
        }
    }

    /**
     * Sets gameOver to true, displays GAME OVER message and buttons.
     */
    private void setGameOver() {
        gameOver = true;

        messageText = new GraphicsText("GAME OVER!", 200, 400);
        messageText.setFontSize(40);
        messageText.setFillColor(Color.RED);
        canvas.add(messageText);

        showButtons();
    }

    /**
     * Creates and adds Restart and Exit buttons to the canvas.
     */
    private void showButtons() {
        restartButton = new Button("Restart");
        exitButton = new Button("Exit");

        restartButton.setPosition(200, 450);
        exitButton.setPosition(330, 450);

        canvas.add(restartButton);
        canvas.add(exitButton);

        restartButton.onClick(() -> restartGame());
        exitButton.onClick(() -> System.exit(0));
    }

    /**
     * Restarts the game by resetting game variables, and adding points and rules back to the canvas.
     */
    private void restartGame() {
        canvas.removeAll();

        score = 0;
        gameOver = false;
        gameWin = false;
        caughtFish = null;

        drawBackground();

        scoreLabel = new GraphicsText("Score: 0", 20, 30);
        scoreLabel.setFontSize(24);
        canvas.add(scoreLabel);
        rulesText = new GraphicsText(
            "Rules:\n" +
            "Press SPACE to fish\n" +
            "Shark = Game Over\n" +
            "Whale or Squid = +100\n" +
            "Tuna = +70\n" +
            "Other Fish = +40",
            CANVAS_WIDTH - 170,40
        );
        rulesText.setFontSize(14);
        rulesText.setFillColor(Color.BLACK);
        canvas.add(rulesText);

        fishes = Fish.generateFish();
        generateAllFish();

        boat = new Boat(CANVAS_WIDTH / 2 , WATER_LEVEL - 570);
        canvas.add(boat.getGraphicsObject());

        fishingLine = new FishingLine(canvas, boat.getX(), boat.getY() + 40);
    }

}
