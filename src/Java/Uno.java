package Java;

import java.util.ArrayList;
import java.util.Scanner;

public class Uno implements PlayerInterface{

    private playerOne p1 = new playerOne();
    private AI bot = new AI();
    private Deck deck = new Deck();
    private ArrayList<Card> table = new ArrayList<>();
    private ArrayList<Player> playerList = new ArrayList<>();
    private Scanner move = new Scanner(System.in);
    private Scanner choice = new Scanner(System.in);
    private Scanner scanner = new Scanner(System.in);
    private Boolean winner = false;

    //startGame function to initialize game
    public void startGame() {

        deck.dealHands(p1.getPlayerOneHand());
        deck.dealHands(bot.getAIHand());
        for(int i = 0; i < playerList.size(); i++){
            playerList.add(i,playerList.get(i));
        }
        while(!winner){
            playerTurn(table, playerList);
        }
    }

    public void playerTurn(ArrayList<Card> table, ArrayList<Player> playerList){

        deck.display(p1.getPlayerOneHand(), "hand");
        System.out.println("Make your move!\n"
                            + "1 - Play cards\n"
                            + "2 - Draw card\n"
                            + "3 - Quit");

        int turn = move.nextInt();
        switch (turn) {
            case 1:

                System.out.println("Would you like to play a normal or special card?");
                String play = choice.nextLine();
                play.toLowerCase();

                switch (play){
                    case "normal":

                        System.out.println("Which cards would you like to play? ");
                        System.out.println("====================================");
                        String input = scanner.nextLine();
                        input = input.replaceAll("\\[", "").replaceAll("\\]", "");
                        String[] stringArray = input.split(", ");

                        for(String s: stringArray) {
                            char color = s.charAt(0);
                            int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
                            for(Card c : p1.getPlayerOneHand()) {
                                if(c.getColour() == color && c.getRank() == value) {
                                    table.add(c);
                                    p1.getPlayerOneHand().remove(c);
                                    break;
                                }
                            }
                        }

                        deck.display(table,"table");
                        playerTurn(table, playerList);
                        break;

                    case "special":

                        System.out.println("Which cards would you like to play? ");
                        System.out.println("====================================");

                        String inputX = scanner.nextLine();
                        input = inputX.replaceAll("\\[", "").replaceAll("\\]", "");
                        String[] stringArrayX = input.split(", ");
                        System.out.println(stringArrayX[0]);

                        for (String s : stringArrayX) {
                            for (Card c : p1.getPlayerOneHand()) {
                                if (stringArrayX[0].equals(c.getAbilityX())) {
                                    table.add(c);
                                    p1.getPlayerOneHand().remove(c);
                                    break;
                                } else if (c.getColour() == s.charAt(0) && c.getAbility().equals(s.substring(2))) {
                                    table.add(c);
                                    p1.getPlayerOneHand().remove(c);
                                    break;
                                }
                            }
                        }

                        deck.display(table,"table");
                        playerTurn(table, playerList);
                        break;

                    default:
                        break;
                }
                   break;


            case 2:
                p1.getPlayerOneHand().add(deck.draw());
                System.out.println("You drew one card and ended your turn.\n");
                bot.playerTurn(table, playerList);
                break;

            case 3:
                System.out.println("Exiting game..");
                System.exit(0);
                break;

            default:
                break;
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



