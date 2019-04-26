package Java;

import java.util.ArrayList;
import java.util.Scanner;

public class Uno {
    //All game logic will reside in this class
    private playerOne p1 = new playerOne();
    private AI bot = new AI();
    private Deck deck = new Deck();
    private ArrayList<Card> field = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void mainMenu() {
        System.out.println("Bienvenido a mi juego de Java.Uno!\n"
                + "Choose one of the options.\n"
                + "1 - New Game\n"
                + "2 - Exit");

        deck.dealHands(p1.getPlayerOneHand());
        deck.dealHands(bot.getAIHand());


        int opt = scanner.nextInt();
        switch (opt) {
            case 1:
                deck.displayHand(p1.getPlayerOneHand());
                startGame();
            default:
                break;
        }
    }


    //    //startGame function to initialize game
    public void startGame() {
        p1.playerTurn();
    }
}

//
//    //make each player play their turn
//    //player goes first and plays game on console
//    //Java.AI will play by having their hand looped through to check for playable cards
//    //if they receive an ability card, they will play it right away
//    //check if deck is empty (if it's not, then next player goes)
//    //if no play can be made, draw card from deck if deck is not empty
//    //else, call reuse pile function

    //check for empty deck (if it is then reuse pile)

    //invalid move function for main player?

    //check for winner function



