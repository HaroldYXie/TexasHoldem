import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand(ArrayList<Card> cards){
        hand = cards;
    }
    public static String evalWinner(Player p1, Player p2, ArrayList<Card> table) {
        p1.hand.addAll(table);
        p2.hand.addAll(table);
        Hand p1hand = new Hand(p1.hand);
        Hand p2hand = new Hand(p2.hand);
        if (p1hand.eval7Cards() > p2hand.eval7Cards()) {
            return "All players are equal; there are no winners";
        } else {
            return "Player 1 won, screw you player 0";
        }
    }
    public double eval7Cards() {
        double max = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                ArrayList<Card> idk = (ArrayList<Card>) hand.clone();
                idk.remove(j);
                idk.remove(i);
                Hand thing = new Hand(idk);
                max = Math.max(max, thing.getOrder() + 0.001 * getTiebreakerValue());
            }
        }
        return max;
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
        int ord = getOrder();
        if (ord == 8 || ord == 7 || ord == 4 || ord == 3 || ord == 2){
            return identicalCards()%100;
        }else return highestFaceValue();
    }

    public boolean isStraight(){
        int sequenceCounter = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getFaceValue() == hand.get(i + 1).getFaceValue() - 1){
                sequenceCounter++;
            }
        }

        if (sequenceCounter == 4)return true;

        if (hand.get(4).getFaceValue() == 1){
            int seqCounter2 = 0;
            for (int i = 0; i < hand.size() - 2; i++) {
                if (hand.get(i).getFaceValue() == hand.get(i + 1).getFaceValue() - 1)seqCounter2++;
                if (seqCounter2 == 3 && hand.get(0).getFaceValue() == 10) return true;
            }
        }

        return false;
    }

    public boolean isRoyalFlush(){
        if (isFlush()){
            int seqCounter = 0;
            for (int i = 0; i < hand.size() - 2; i++) {
                if (hand.get(i).getFaceValue() == hand.get(i + 1).getFaceValue() - 1)seqCounter++;
            }

            if (seqCounter == 3 && hand.get(4).getFaceValue() == 1 && hand.get(0).getFaceValue() == 10)return true;
        }

        return false;
    }

    public boolean isFlush(){
        int sameCounter = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getSuite() == hand.get(i + 1).getSuite())sameCounter++;
        }

        if (sameCounter == 4) return true;

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
