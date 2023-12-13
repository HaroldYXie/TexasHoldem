import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PImage;

public class Card {
    private int faceValue;
    private int suite;
    private PImage image;

    public Card(int faceValue, int suite, PImage atlas){
        this.faceValue = faceValue;
        this.suite = suite;
        this.image = cropCard(faceValue, suite, atlas);
    }
    public PImage cropCard(int face, int suite, PImage atlas) {
        PImage out = new PImage(44*8, 68*8);
        out.copy(
                atlas,
                33 + face * 391, 33 + suite * 569,
                356, 535,
                0,0,
                out.width, out.height
        );
        out.resize(44,68);
        return out;
    }
    public int getFaceValue(){
        return faceValue;
    }

    public int getSuite(){
        return suite;
    }
    public PImage getImage() {
        return image;
    }
}
