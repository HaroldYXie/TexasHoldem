import processing.core.PApplet;
import processing.core.PImage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends PApplet {
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> table = new ArrayList<>();
    private Player p1 = new Player(1000, true);
    private Player p2 = new Player(1000, false);
    private int board_state = -1;
    private int playerTurn = 0;
    private int timeSinceChange = 0;
    private boolean finishedTurns = false;
    private boolean switchScreen = false;
    private int pot = 0;
    private PImage background;
    private PImage atlas;

    public void settings() {
        size(1000, 492);
    }

    public void setup() {
        background = loadImage("assets/APCS.PokerTable.jpeg");
        atlas = loadImage("assets/atlas.png");
        initializeDeck();
        textSize(20);
    }

    public void draw() {
        clear();
        if (switchScreen) {
            fill(255,255,255);
            text("Player " + playerTurn + "'s turn is over. Press p to continue to your turn", 380, 240);
        } else {
            image(background, 0, 0);
            Player p = playerTurn == 0 ? p1 : p2;
            Player other = playerTurn == 0 ? p2 : p1;

            for (int i = 0; i < table.size(); i++) {
                image(table.get(i).getImage(), 360 + i * 58, 210);
            }
            for (int i = 0; i < p.hand.size(); i++) {
                image(p.hand.get(i).getImage(),500 + (i - (float) p.hand.size() / 2) * 58,400);
            }
            fill(0,0,0);
            text("You are player: " + playerTurn, 0,50);
            text("You have: " + p.chips, 0, 75);
            text("Prize pool is: " + pot, 0,100 );
            text("You are betting: " + p.bet, 0, 125);
            text("Other has bet: " + other.bet, 0, 150);
            if (timeSinceChange > 20) {
                timeSinceChange = 0;
                handleGameRound(p);
            } else {
                timeSinceChange += 1;
            }
        }
    }
    public void keyPressed() {
        if ((key == 'p' || key == 'P') && switchScreen) {
            switchScreen = false;
            playerTurn = (playerTurn + 1) % 2;
        }
    }
    public void initializeDeck(){
        for (int value = 0; value < 13; value++) {
            for (int suite = 0; suite < 4; suite++) {
                deck.add(new Card(value, suite, atlas));
            }
        }
        Collections.shuffle(deck);
    }
    public void handleGameRound(Player p) {
        if (p1.bet == p2.bet && p1.bet > 0) {
            finishedTurns = true;
            board_state += 1;
        }
        if (!finishedTurns) {
            p.act();
            switchScreen = true;
            return;
        }
        finishedTurns = false;
        pot += p1.bet;
        pot += p2.bet;

        p1.chips -= p1.bet;
        p2.chips -= p2.bet;

        p1.bet = 0;
        p2.bet = 0;

        if (board_state == 0) {
            for (int i = 0; i < 2; i++) {
                p1.hand.add(deck.remove(0));
                p2.hand.add(deck.remove(0));
            }
        }
        if (board_state == 1) {
            for (int i = 0; i < 3; i++) {
                table.add(deck.remove(0));
            }
        }
        if (board_state == 2) {
            table.add(deck.remove(0));
        }
        if (board_state == 3) {
            table.add(deck.remove(0));
            System.out.println(Hand.evalWinner(p1,p2,table));
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
