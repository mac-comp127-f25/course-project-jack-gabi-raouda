import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;


public class Fish {
    private static List<Double> fishSizes = List.of(  70.0, 90.0, 110.0, 130.0, 150.0 );

    private double centerX;
    private double centerY;
    private Image image;
    private double dx;
    private double dy;
    private double speed = 1.5;
    private boolean caught = false;


    public static List<String> fishImages = List.of(
        "fish_images/fish1.png",
        "fish_images/fish2.png",
        "fish_images/fish3.png",
        "fish_images/fish4.png",
        "fish_images/fish5.png",
        "fish_images/fish6.png", 
        "fish_images/fish7.png"
    );

    public Fish(double centerX, double centerY, double size, Image image) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.image = image;
        this.image.setMaxHeight(size);
        this.image.setMaxWidth(size);
        this.image.setCenter(centerX, centerY);
        System.out.println("Created fish at (" + image.getCenter() + ") with size " + size);

    }
 
    /**
     * Picks random size from the fishSizes list.
     */  
    public static double pickRandomSize() {
        return fishSizes.get((int)(Math.random() * fishSizes.size()));
    }

    /**
     * Picks random image path from the fishImages list.
     */
    public static Image pickRandomImage() {
        String randomPath = fishImages.get((int)(Math.random() * fishImages.size()));
        return new Image(randomPath);
    }

     /**
     * Returns the image of the fish.
     */  
    public Image getImage(){
        return image;
    }

    /**
     * Returns boolean of whether the fish is caught.
     */  
    public boolean isCaught() {
        return caught;
    }

    /**
     * Changes the state of the fish to caught, changing the caught boolean to true.
     */  
    public void catchFish() {
        caught = true;
    }

    /**
     * Sets the position of the fish to the x and y coordinate of the bottom of the fishing line.
     */  
    public void followLine(double x, double y) {
        image.setCenter(x, y);
    }


    /**
     * Creates a list of fish with random sizes and images at random positions within the bounds of the water.
     */
    public static List<Fish> generateFish() {
    List<Fish> fishList = new ArrayList<>();

    final double CANVAS_WIDTH = 600;
    final double WATER_SURFACE_Y = 200;      
    final double WATER_BOTTOM_Y = 800;        

    for (int i = 0; i < 15; i++) {
        double size = pickRandomSize();
        Image image = pickRandomImage();

        double x = Math.random() * (CANVAS_WIDTH - size) + size / 2;
        double y = WATER_SURFACE_Y + Math.random() * ((WATER_BOTTOM_Y - size) - WATER_SURFACE_Y) + size / 2;

        fishList.add(new Fish(x, y, size, image));
    }
        return fishList;
    }

    /**
     * Adds the fish image to the canvas.
     */  
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(image);
    }

    /**
     * Moves the fish randomly up, down, left, and right within the water bounds.
     */
    public void moveFish(double CANVAS_WIDTH, double FISH_Y_BOUND){
        centerX += dx;
        centerY += dy;
        if (centerX < 0 || centerX > CANVAS_WIDTH) {
            dx *= -1;
        }
        if (centerY< FISH_Y_BOUND) {
            centerY = FISH_Y_BOUND;
            dy *= -1;
        }
        if (centerY > 800) {  
        centerY = 800;
        dy *= -1;
    }
        image.setCenter(centerX, centerY);
        if (Math.random() < 0.01) {
            dx = (Math.random() * 2 - 1) * speed;
            dy = (Math.random() * 2 - 1) * speed;
        }
    }
}
