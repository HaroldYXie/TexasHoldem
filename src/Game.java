import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;

public class Game extends PApplet {
    ArrayList<Card> deck;
    PImage background;

    public void settings() {
        size(1000, 492);

    }

    public void setup() {
        background = loadImage("APCS.PokerTable.jpeg");
        deck = new ArrayList<>();
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        image(background, 0, 0);
    }

    public void initializeDeck(){
        for (int value = 1; value <= 13; value++) {
            for (int suite = 1; suite <= 4; suite++) {
                Card card = new Card(value, suite);
                deck.add(card);
            }
        }

        Collections.shuffle(deck);
    }

    public void initializeBoard(){
        initializeDeck();
        Player P1 = new Player(1000, true);
        Player P2 = new Player(1000, false);
    }

    public void playerSwitch(int playerNum){
        fill(0, 0 , 0);
        rect(0, 0, 1000, 492);
        fill(255, 255, 255);
        text("Player " + playerNum + "'s turn is over. Press p to continue to your turn", 400, 240);
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
