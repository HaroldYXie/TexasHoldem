import javax.swing.*;
import java.util.ArrayList;

public class Player {

    private int chips;
    private int bet;
    private boolean bigBind;
    ArrayList<Card> hand = new ArrayList<>();


    public Player(int chips, boolean bigBind){
        this.chips = chips;
        this.bigBind = bigBind;
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
        String[] options = {"Check", "Bet", "Fold"};

        int selection = JOptionPane.showOptionDialog(null, "Select One: ", "Main Window", 0, 3, null, options, options[0]);

        if (selection == 1){
            int betValue = Integer.parseInt(JOptionPane.showInputDialog("How much would you like to bet?"));
        }
    }
}
