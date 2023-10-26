import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();

    public Hand(ArrayList<Card> cards){
        hand = cards;
    }

    public int getOrder(){
        if (isRoyalFlush()){
            return 10;
        } else if (isStraight() && isFlush()) {
            return 9;
        } else if (identicalCards() >= 400 && identicalCards() <= 499) {
            return 8;
        } else if (identicalCards() >= 500) {
            return 7;
        } else if (isFlush()) {
            return 6;
        } else if (isStraight()) {
            return 5;
        } else if (identicalCards() >= 300 && identicalCards() <= 399) {
            return 4;
        } else if (identicalCards() >= 200 && identicalCards() <= 299) {
            return 3;
        } else if (identicalCards() >= 100 && identicalCards() <= 199) {
            return 2;
        }else return 1;
    }

    public int getTiebreakerValue(){
        if (identicalCards() >= 400 && identicalCards() <= 499) {
            return identicalCards() - 400;
        } else if (identicalCards() >= 500) {
            return identicalCards() - 500;
        } else if (identicalCards() >= 300 && identicalCards() <= 399) {
            return identicalCards() - 300;
        } else if (identicalCards() >= 200 && identicalCards() <= 299) {
            return identicalCards() - 200;
        } else if (identicalCards() >= 100 && identicalCards() <= 199) {
            return identicalCards() - 100;
        }else return highestFaceValue();
    }

    public boolean isStraight(){
        int sequenceCounter = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getFaceValue() == hand.get(i + 1).getFaceValue() + 1){
                sequenceCounter++;
            }
        }

        if (sequenceCounter == 4){
            return true;
        }

        return false;
    }

    public boolean isRoyalFlush(){
        if (isStraight() && isFlush()){
            if (hand.get(0).getFaceValue() == 13){
                return true;
            }
        }

        return false;
    }

    public boolean isFlush(){
        int sameCounter = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getSuite() == hand.get(i + 1).getSuite()){
                sameCounter++;
            }
        }

        if (sameCounter == 4){
            return true;
        }

        return false;
    }

    public int identicalCards(){
        int one = hand.get(0).getFaceValue();
        int two = hand.get(1).getFaceValue();
        int three = hand.get(2).getFaceValue();
        int four = hand.get(3).getFaceValue();
        int five = hand.get(4).getFaceValue();

        if (one == two && one == three && four == five){
            return 500 + Math.max(one, four);
        }else if (one == two && one == three && one == four){
            return 400 + one;
        } else if (one == two && one == three) {
            return 300 + one;
        } else if (one == two) {
            if (three == four){
                return 200 + Math.max(one, three);
            }else return 100 + one;
        }

        return 0;
    }

    public int highestFaceValue(){
        int currHighest = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getFaceValue() > currHighest){
                currHighest = hand.get(i).getFaceValue();
            }
        }

        return currHighest;
    }

}
