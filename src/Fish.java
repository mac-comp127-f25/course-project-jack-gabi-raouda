import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.Image;

import edu.macalester.graphics.GraphicsObject;


public class Fish {
    //height + width range code? maybe a list of sizes to randomly choose from
    private List<Double> fishSizes = java.util.Arrays.asList(20.0, 30.0, 40.0, 50.0, 60.0);
    private List<Image> fishImages;

    private double centerX;
    private double centerY;

    public Image fish1 = new Image("fish_images/fish1.png");
    public Image fish2 = new Image("fish_images/fish2.png");
    public Image fish3 = new Image("fish_images/fish3.png");
    public Image fish4 = new Image("fish_images/fish4.png");
    public Image fish5 = new Image("fish_images/fish5.png");

    public Fish(double centerX, double centerY, double size, Image image) {
        this.centerX = centerX;
        this.centerY = centerY;

        //code that randomly picks an image and a size from the list and creates the graphic object
        fishImages = java.util.Arrays.asList(fish1, fish2, fish3, fish4, fish5);
        image = fishImages.get((int)(Math.random() * fishImages.size()));
        image.setCenter(centerX, centerY);
        size = fishSizes.get((int)(Math.random() * fishSizes.size()));
        image.setMaxHeight(size);
        image.setMaxWidth(size);

    }
 

    public GraphicsObject getGraphicObject() {
        //return the graphic object representing the fish
        return null;
    }

}
