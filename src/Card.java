public class Card {
    int faceValue;
    int suite;

    public Card(int faceValue, int suite){
        this.faceValue = faceValue;
        this.suite = suite;
    }

    public int getFaceValue(){
        return faceValue;
    }

    public int getSuite(){
        return suite;
    }
}