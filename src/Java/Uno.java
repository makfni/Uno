package Java;

import java.util.ArrayList;
import java.util.Scanner;

public class Uno extends Deck implements PlayerInterface{

    private playerOne p1 = new playerOne();
    private AI bot = new AI();
    private Deck deck = new Deck();
    private ArrayList<Card> table = new ArrayList<>();
    private ArrayList<ArrayList<Card>> playerList = new ArrayList<>();
    private Scanner move = new Scanner(System.in);
    private Scanner choice = new Scanner(System.in);
    private Scanner scanner = new Scanner(System.in);
    private Scanner colourChange = new Scanner(System.in);

    //startGame function to initialize game
    private void startGame() {

        deck.dealHands(p1.getPlayerOneHand());
        deck.dealHands(bot.getAIHand());

        //First card drawn on the table cannot be a wild card
        while(table.isEmpty()) {
            if (draw().isWildCard()) {
                System.out.println("here");
                shuffleDeck(deck.getDeck());
            } else {
                if(draw().isActionCard()){
                    System.out.println("there");
                    
                    table.add(draw());
                }
            }
        }

        //p1 is at index 0
        playerList.add(p1.getPlayerOneHand());
        //bot is at index 1
        playerList.add(bot.getAIHand());

        //Keeps the game going until someone wins or the deck runs out of cards
        while(!p1.getPlayerOneHand().isEmpty() && !bot.getAIHand().isEmpty()) {
            for(int i = 0; i < playerList.size(); i++) {

               System.out.println("Deck size: " + deck.getDeckNum() + " \n");
               deck.display(table,"table");

               if(playerList.get(i) == p1.getPlayerOneHand()){
                   playerTurn(table, playerList.get(i));
               }else if (playerList.get(i) == bot.getAIHand()){
                   bot.playerTurn(table, playerList.get(i));
               }
            }
        }

    }

    public void playerTurn(ArrayList<Card> table, ArrayList<Card> player){
        if(checkWinner(p1.handSize(p1.getPlayerOneHand()))){
            System.exit(0);
        }

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

                switch (play.toUpperCase()) {
                    case "NORMAL":

                        System.out.println("Which cards would you like to play? ");
                        System.out.println("====================================");
                        String input = scanner.nextLine();
                        // input = input.replaceAll("\\[", "").replaceAll("\\]", "");
                        String[] stringArray = input.split(", ");

                        System.out.println(stringArray[0]);

                        for (String s : stringArray) {
                            char color = s.charAt(0);
                            int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
                            for (Card c : p1.getPlayerOneHand()) {
                                if (c.getColour() == color && c.getRank() == value) {
                                    if (checkValidity(table, c, play)) {
                                        table.add(c);
                                        p1.getPlayerOneHand().remove(c);
                                        break;
                                    } else {
                                        System.out.println("Invalid move!");
                                    }
                                }
                            }
                        }
                        return;

                    case "SPECIAL":

                        System.out.println("Which cards would you like to play? ");
                        System.out.println("====================================");

                        String inputX = scanner.nextLine();
                        // input = inputX.replaceAll("\\[", "").replaceAll("\\]", "");
                        String[] stringArrayX = inputX.split(", ");

                        System.out.println(stringArrayX[0]);

                        for (String arrayX : stringArrayX) {

                            char colour = arrayX.charAt(0);
                            String actionCard = arrayX.substring(2);

                            for (Card c : p1.getPlayerOneHand()) {
                                if (arrayX.equals(c.getWildCard())) {
                                    specialEffect(p1.getPlayerOneHand(), c, "wildCard");
                                    table.add(0,c);
                                    p1.getPlayerOneHand().remove(c);
                                    if(arrayX.equals("Change colour")){
                                        playerTurn(table, playerList.get(0));
                                        break;
                                    }
                                    break;
                                } else if (c.getColour() == colour && actionCard.equals(c.getActionCard())) {
                                    if (checkValidity(table, c, play)) {
                                        table.add(c);
                                        p1.getPlayerOneHand().remove(c);
                                        specialEffect(p1.getPlayerOneHand(), c, "actionCard");
                                        break;
                                    } else {
                                        System.out.println("Invalid move!");
                                    }
                                }
                            }
                        }
                        return;


                    default:
                        break;
                }


            case 2:
                p1.getPlayerOneHand().add(deck.draw());
                deck.display(p1.getPlayerOneHand(), "hand");
                System.out.println("You drew one card and ended your turn.\n");
                return;

            case 3:
                System.out.println("Exiting game..");
                System.exit(0);
                break;

            default:
                break;

        }
    }

    public void specialEffect(ArrayList<Card> hand, Card card, String special) {
        if (special.equals("actionCard")) {
            switch (card.getActionCard()) {
                case "Skip turn":
                    playerTurn(table, playerList.get(0));
                    break;
                case "Reverse":
                    return;
                case "Draw two":
                    for (int i = 0; i < 2; i++) {
                       bot.getAIHand().add(deck.draw());
                    }
                    break;
                default:
                    break;
                }
        } else if (special.equals("wildCard")) {
            switch (card.getWildCard()) {
                case "Change colour":
                    System.out.println("What colour would you like?");
                    String input = colourChange.nextLine();
                    char colour = input.charAt(0);

                    for(int i = 0; i < deck.getColour().length; i++){
                        if(colour == deck.getColour()[i]) {
                            table.get(table.size() - 1).setColour(colour);
                            break;
                        }
                    }
                    break;
                case "Draw four":
                    for (int i = 0; i < 4; i++) {
                        playerList.get(1).add(deck.draw());
                    }
                    break;
                default:
                    break;

            }
        }
    }


    public Boolean checkValidity(ArrayList<Card> table, Card card, String play) {

        char tableColour = table.get(table.size() - 1).getColour();
        int tableRank = table.get(table.size() - 1).getRank();
        String actionCard = table.get(table.size() - 1).getActionCard();

        if (play.equals("normal")) {
            return card.getColour() == tableColour || card.getRank() == tableRank;
        } else if (play.equals("special")) {
            return card.getColour() == tableColour || card.getActionCard().equals(actionCard);
        }
        return false;
    }

    public Boolean checkWinner(int size){
        if(size == 0) {
            System.out.println("You win!");
            return true;
        }
        return false;
    }

    public ArrayList<Card> getTable(){
        return table;
    }
}
