import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;

public class Game extends PApplet {
    private ArrayList<Card> table;
    private Player P1;
    private Player P2;
    private int playerTurn = 0;
    private boolean switchScreen = false;
    private PImage background;

    public void settings() {
        size(1000, 492);
    }

    public void setup() {
        background = loadImage("assets/APCS.PokerTable.jpeg");
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        if (switchScreen) {
            fill(255, 255, 255);
            text("Player " + playerTurn + "'s turn is over. Press p to continue to your turn", 380, 240);
        } else {
            image(background, 0, 0);

        }
    }
    public void keyPressed() {
        if ((key == 'p' || key == 'P') && switchScreen) {
            switchScreen = false;
        }
    }
    public ArrayList<Card> initializeDeck(){
        ArrayList<Card> deck = new ArrayList<>();

            for (int value = 1; value <= 13; value++) {
                for (int suite = 1; suite <= 4; suite++) {
                    deck.add(new Card(value, suite));
                }
            }
        Collections.shuffle(deck);

            return deck;
    }

    public void initializeBoard(){
        P1 = new Player(2000, true);
        P2 = new Player(2000, false);
    }

    public void runGame(){
        ArrayList<Card> deck = initializeDeck();
        for (int i = 0; i < 3; i++) {
            table.add(deck.remove(0));
        }

        ArrayList<Card> P1Hand = new ArrayList<>();
        ArrayList<Card> P2Hand = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            P1Hand.add(deck.remove(0));
            P2Hand.add(deck.remove(0));
        }

        P1.giveHand(P1Hand);
        P2.giveHand(P2Hand);
    }


    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
