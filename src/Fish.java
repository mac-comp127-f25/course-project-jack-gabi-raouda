import java.awt.Image;
import java.util.List;

import edu.macalester.graphics.GraphicsObject;


public class Fish {
    //height + width range code? maybe a list of sizes to randomly choose from
    private List<Double> fishSizes = java.util.Arrays.asList(20.0, 30.0, 40.0, 50.0, 60.0);
    private List<Image> fishImages;

    private double centerX;
    private double centerY;

    public Fish(double centerX, double centerY, double width, double height, Image image) {
        this.centerX = centerX;
        this.centerY = centerY;

        //code that randomly picks a size from the list and creates the graphic object
        fishImages = java.util.Arrays.asList(

            //load fish images here
        );

    }
    public GraphicsObject getGraphicObject() {
        //return the graphic object representing the fish
        return null;
    }

}
