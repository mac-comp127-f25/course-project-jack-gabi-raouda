
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;


public class Fish {
    private static List<Double> fishSizes = java.util.Arrays.asList(20.0, 30.0, 40.0, 50.0, 60.0);

    private double centerX;
    private double centerY;
    private Image image;

    public static Image fish1 = new Image("fish_images/fish1.png");
    public static Image fish2 = new Image("fish_images/fish2.png");
    public static Image fish3 = new Image("fish_images/fish3.png");
    public static Image fish4 = new Image("fish_images/fish4.png");
    public static Image fish5 = new Image("fish_images/fish5.png");
    public static List<Image> fishImages = java.util.Arrays.asList(fish1, fish2, fish3, fish4, fish5);

    public Fish(double centerX, double centerY, double size, Image image) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.image = image;
        this.image.setCenter(centerX, centerY);
        this.image.setMaxHeight(size);
        this.image.setMaxWidth(size);
    }
 
    public static double pickRandomSize() {
        return fishSizes.get((int)(Math.random() * fishSizes.size()));
    }

    public static Image pickRandomImage() {
        return fishImages.get((int)(Math.random() * fishImages.size()));
    }

    // public GraphicsObject getGraphicObject() {
    //     return ;
    // }

    public Image getImage(){
        return image;
    }



    public static List<Fish> generateFish(){
        List <Fish> fishList = new ArrayList<>();
        fishList.add(new Fish(100, 500, pickRandomSize(), pickRandomImage()));
        fishList.add(new Fish(200, 500, pickRandomSize(), pickRandomImage()));
        fishList.add(new Fish(300, 500, pickRandomSize(), pickRandomImage()));
        fishList.add(new Fish(400, 500, pickRandomSize(), pickRandomImage()));
        fishList.add(new Fish(500, 550, pickRandomSize(), pickRandomImage()));
        return fishList;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(image);
    }

}
