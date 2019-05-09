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

        //p1 and bot are index 0 and 1 respectively
        playerList.add(p1.getPlayerOneHand());
        playerList.add(bot.getAIHand());

        //Starts game off by taking a card on the top of the deck
        //and placing it on the table.
        //First card drawn on the table cannot be a wild card.
        while(table.isEmpty()) {
            if (getDeck().get(getDeckNum()-1).isWildCard()) {
                shuffleDeck(getDeck());
            } else if (getDeck().get(getDeckNum()-1).isActionCard()) {
                table.add(draw());
                initAction(p1.getPlayerOneHand(), table.get(0));
            } else {
                table.add(draw());
            }
        }

        //Keeps the game going until someone wins or the deck runs out of cards.
        while(!p1.getPlayerOneHand().isEmpty() && !bot.getAIHand().isEmpty()) {
            for (ArrayList<Card> cards : playerList) {

                System.out.println("Deck size: " + deck.getDeckNum() + " \n");
                deck.display(table, "table");

                if (cards == p1.getPlayerOneHand()) {
                    playerTurn(table, cards);
                } else if (cards == bot.getAIHand()) {
                    bot.playerTurn(table, cards);
                }
            }
        }

    }

    //p1's playerTurn
    public void playerTurn(ArrayList<Card> table, ArrayList<Card> player){
        if(checkWinner(p1.getHandSize())){
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

                        //System.out.println(stringArray[0]);

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

                        //System.out.println(stringArrayX[0]);

                        for (String arrayX : stringArrayX) {

                            char colour = arrayX.charAt(0);
                            String actionCard = arrayX.substring(2);

                            for (Card c : p1.getPlayerOneHand()) {
                                if (arrayX.equals(c.getWildCard())) {
                                    specialEffect(bot.getAIHand(), c, "wildCard");
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
                                        specialEffect(bot.getAIHand(), c, "actionCard");
                                        break;
                                    } else {
                                        System.out.println("Invalid move!");
                                        playerTurn(table, p1.getPlayerOneHand());
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

    //Depending on whether it's an action or wild card, this method will
    //affect your opponent..or you.
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
                       hand.add(deck.draw());
                    }
                    break;
                default:
                    break;
                }
        } else if (special.equals("wildCard")) {
            switch (card.getWildCard()) {
                case "Change colour":
                    System.out.println("What colour would you like?\n");
                    String input = colourChange.nextLine();
                    char colour = input.charAt(0);

                    int i = 0;
                    while (i < deck.getColour().length) {
                        if (colour == deck.getColour()[i]) {
                            table.get(table.size() - 1).setColour(colour);
                            break;
                        }
                        i++;
                    }
                    break;
                case "Draw four":
                    for (int j = 0; j < 4; j++) {
                        hand.add(deck.draw());
                    }
                    break;
                default:
                    break;

            }
        }
    }

    //Similar to specialEffect() except this method is strictly for
    //initializing the table.
    public void initAction(ArrayList<Card> hand, Card card){
        switch (card.getActionCard()) {
            case "Skip turn":
                System.out.println("First card on the table made you skip your turn.\n");
                bot.playerTurn(table, playerList.get(1));
                break;
            case "Reverse":
                System.out.println("First card on the table made you Reverse.\n");
                bot.playerTurn(table, playerList.get(1));
                return;
            case "Draw two":
                for (int i = 0; i < 2; i++) {
                    hand.add(draw());
                }
                break;
            default:
                break;
        }
    }


    //This method will check to see if the card you decided to play
    //is a valid move according to the card on top of the pile
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

    //Checks for winner
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
