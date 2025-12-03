
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.GraphicsObject;


public class Fish {
    private List<Double> fishSizes = java.util.Arrays.asList(20.0, 30.0, 40.0, 50.0, 60.0);

    private double centerX;
    private double centerY;
    private Image fish;

    public Image fish1 = new Image("fish_images/fish1.png");
    public Image fish2 = new Image("fish_images/fish2.png");
    public Image fish3 = new Image("fish_images/fish3.png");
    public Image fish4 = new Image("fish_images/fish4.png");
    public Image fish5 = new Image("fish_images/fish5.png");
    public List<Image> fishImages = java.util.Arrays.asList(fish1, fish2, fish3, fish4, fish5);

    public Fish(double centerX, double centerY, double size, Image image) {
        this.centerX = centerX;
        this.centerY = centerY;

        image.setCenter(centerX, centerY);
        image.setMaxHeight(size);
        image.setMaxWidth(size);
        this.fish = image;
    }
 
    public double pickRandomSize() {
        return fishSizes.get((int)(Math.random() * fishSizes.size()));
    }

    public Image pickRandomImage() {
        return fishImages.get((int)(Math.random() * fishImages.size()));
    }

    // public GraphicsObject getGraphicObject() {
    //     //return the graphic object representing the fish
    //     return null;
    // }

    public Image getImage(){
        return fish;
    }

    public List<Fish> generateFish(){
        List <Fish> fishList = new ArrayList<>();
        fishList.add(new Fish(100, 600, pickRandomSize(), pickRandomImage()));
        fishList.add(new Fish(200, 500, pickRandomSize(), pickRandomImage()));
        fishList.add(new Fish(300, 700, pickRandomSize(), pickRandomImage()));
        fishList.add(new Fish(400, 550, pickRandomSize(), pickRandomImage()));
        fishList.add(new Fish(500, 650, pickRandomSize(), pickRandomImage()));
        return fishList;
    }

}
