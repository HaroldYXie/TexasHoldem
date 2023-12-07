import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PImage;

public class Card {
    private int faceValue;
    private int suite;

    private PImage image;

    public Card(int faceValue, int suite){
        this.faceValue = faceValue;
        this.suite = suite;
    }
    public String getImagePath() {
        return "assets/card/card" + suite + "-" + faceValue + ".png";
    }
    public int getFaceValue(){
        return faceValue;
    }

    public int getSuite(){
        return suite;
    }
}
