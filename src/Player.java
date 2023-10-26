import javax.swing.*;
import java.util.ArrayList;

public class Player {

    int chips;
    int bet;
    boolean bigBind;
    ArrayList<Card> hand = new ArrayList<>();


    public Player(int chips, boolean bigBind){
        this.chips = chips;
        this.bigBind = bigBind
    }

    public void bet(int bet){
        if (bet > 0 && bet <= chips){
            chips -= bet;
        }
    }

    public void replaceHand(ArrayList<Card> newHand){
        hand = newHand;
    }

    public void call(int minChips){
        chips -= minChips;
    }

    public void fold(){
        chips -= bet;
    }

    public void act(){
        int decision = JOptionPane.showOptionDialog()
    }
}
