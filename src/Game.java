import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;

public class Game extends PApplet {
    private ArrayList<Card> deck;
    private ArrayList<Card> table;
    private ArrayList<Player> players;
    private int playerTurn = 0;
    private boolean switchScreen = false;
    private int pot = 0;
    private PImage background;

    public void settings() {
        size(1000, 492);
    }

    public void setup() {
        background = loadImage("assets/APCS.PokerTable.jpeg");
        deck = new ArrayList<>();
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
            playerTurn = (playerTurn + 1) % players.size();
        }
    }
    public void initializeDeck(int amount){
        // Common in casinos to use multiple decks together to prevent card counting
        for (int i = 0; i < amount; i++) {
            for (int value = 1; value <= 13; value++) {
                for (int suite = 1; suite <= 4; suite++) {
                    deck.add(new Card(value, suite));
                }
            }
        }
        Collections.shuffle(deck);
    }

    public void initializeBoard(){
        initializeDeck(3);
        Player P1 = new Player(1000, true);
        Player P2 = new Player(1000, false);
    }


    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
