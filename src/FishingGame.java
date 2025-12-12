import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.GraphicsGroup;  


/**
 * Class implementing the fishing game.
 * 
 * @author Raouda Mamane Bello Boubacar 
 * @author Jack Fang
 * @author Gabi Palladino
 *
 * This class creates the main game window, manages game logic, and handles user interactions through 
 * mouse movement, keyboard events, and pressable buttons. 
 */
public class FishingGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private static final double WATER_LEVEL = CANVAS_HEIGHT - 150;
    private static final double FISH_Y_BOUND = 170;
    private FishingLine fishingLine;
    private Boat boat;
    private List<Fish> fishes = Fish.generateFish();
    private Fish caughtFish = null;
    private int score = 0;
    private boolean gameOver = false;
    private boolean gameWin = false;
    private Button restartButton;
    private Button exitButton;
    private Button rulesButton;
    private Rectangle rulesBox;
    private GraphicsText rulesText;
    private Button closeRulesButton;
    private boolean rulesVisible = false;
    private GraphicsGroup scoreGroup;
    private GraphicsText scoreText;

    public CanvasWindow canvas;
    
    public FishingGame() {
        canvas = new CanvasWindow("Fishing!", CANVAS_WIDTH, CANVAS_HEIGHT);
        drawBackground();

        createRulesButton();
        createScoreDisplay();


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
     * Creates a score display
     */
    private void createScoreDisplay() {
        scoreGroup = new GraphicsGroup(0, 0);

        Rectangle scoreBox = new Rectangle(0, 0, 160, 40);
        scoreBox.setFillColor(new Color(210, 245, 210));
        scoreBox.setStrokeColor(new Color(180, 180, 180));

        scoreText = new GraphicsText("Score: 0", 15, 26);
        scoreText.setFontSize(16);

        scoreGroup.add(scoreBox);
        scoreGroup.add(scoreText);

        scoreGroup.setPosition(
            CANVAS_WIDTH - 160 - 20,
            70 
        );
        canvas.add(scoreGroup);
    }

    /**
    * Creates and adds the Rules button to the canvas.
    */
    private void createRulesButton() {
    rulesButton = new Button("Rules");
    rulesButton.setPosition(CANVAS_WIDTH - 100, 20);
    canvas.add(rulesButton);
    rulesButton.onClick(() -> toggleRules());
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
            if (caughtFish != null) 
                return; 

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
                    scoreText.setText("Score: " + score);

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
        GraphicsText messageText = new GraphicsText("GAME OVER!", 200, 400);
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

        createRulesButton();
        createScoreDisplay();


        fishes = Fish.generateFish();
        generateAllFish();

        boat = new Boat(CANVAS_WIDTH / 2 , WATER_LEVEL - 570);
        canvas.add(boat.getGraphicsObject());

        fishingLine = new FishingLine(canvas, boat.getX(), boat.getY() + 40);

    }

        private void toggleRules() {
        if (rulesVisible) {
            canvas.remove(rulesBox);
            canvas.remove(rulesText);
            canvas.remove(closeRulesButton);
            rulesVisible = false;
            return;
        }

        rulesBox = new Rectangle(50, 150, 500, 400);
        rulesBox.setFillColor(new Color(0, 0, 0, 180));
        rulesBox.setStroked(false);

        rulesText = new GraphicsText(
            "WELCOME TO OUR FISHING GAME!\n\n" +
            "Catch fish to earn points, but be careful!\n" +
            "hooking a shark will be deadly!\n\n" +
            "Small Fish: +40 points\n" +
            "Tuna: +70 points\n" +
            "Whale / Squid: +100 points\n" +
            "Shark: GAME OVER\n\n" +
            "SPACE — Drop the fishing line\n" +
            "Mouse — Move the boat",    
            130, 200
        );

        rulesText.setFontSize(20);
        rulesText.setFillColor(Color.WHITE);

        closeRulesButton = new Button("Close");
        closeRulesButton.setPosition(260, 480);
        closeRulesButton.onClick(() -> toggleRules());

        canvas.add(rulesBox);
        canvas.add(rulesText);
        canvas.add(closeRulesButton);
        rulesVisible = true;
    }

}
