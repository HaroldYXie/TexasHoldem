import javax.swing.*;
import java.util.ArrayList;

public class Player {

    int chips;
    int bet;
    boolean satisfied;
    private boolean bigBlind;
    ArrayList<Card> hand = new ArrayList<>();


    public Player(int chips, boolean bigBlind){
        this.chips = chips;
        this.bigBlind = bigBlind;
    }

    public void bet(int bet){
        this.chips = this.chips - bet;
    }

    public void giveCard(Card card){
        this.hand.add(card);
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
            bet += betValue;
        }
    }
}
