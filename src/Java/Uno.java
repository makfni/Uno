package Java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Uno implements PlayerInterface{

    private playerOne p1 = new playerOne();
    private AI bot = new AI();
    private Deck deck = new Deck();
    private ArrayList<Card> field = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Boolean winner = false;
    private ArrayList<Player> playerList = new ArrayList<>();

    //startGame function to initialize game
    public void startGame() {

        deck.dealHands(p1.getPlayerOneHand());
        deck.dealHands(bot.getAIHand());

        for(int i = 0; i < playerList.size(); i++){
            playerList.add(i,playerList.get(i));
        }

        while(!winner){
            playerTurn(field, playerList);
        }
    }

    public void playerTurn(ArrayList<Card> field, ArrayList<Player> playerList){

        deck.displayHand(p1.getPlayerOneHand());
        System.out.println("Which cards would you like to play? ");
        System.out.println("====================================");
        String input = scanner.nextLine();
        input = input.replaceAll("\\{", "").replaceAll("\\}", "");
        String[] stringArray = input.split(", ");

        for(String s: stringArray){
            //String
        }
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



