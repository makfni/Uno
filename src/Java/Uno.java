package Java;

import java.util.ArrayList;
import java.util.Scanner;

public class Uno extends Deck implements PlayerInterface {

    private playerOne p1 = new playerOne();
    private AI bot = new AI();
    public Deck deck = new Deck();
    private ArrayList<Card> table = new ArrayList<>();
    private ArrayList<ArrayList<Card>> playerList = new ArrayList<>();
    private Scanner move = new Scanner(System.in);
    private Scanner choice = new Scanner(System.in);
    private Scanner scanner = new Scanner(System.in);
    private Scanner colourChange = new Scanner(System.in);
    private int drawCount;

    //startGame function to initialize game
    public void startGame() {

        deck.dealHands(p1.getPlayerOneHand());
        deck.dealHands(bot.getAIHand());

        System.out.println(getDeckNum());

        //p1 and bot are index 0 and 1 respectively
        playerList.add(p1.getPlayerOneHand());
        playerList.add(bot.getAIHand());

        //Starts game off by taking a card on the top of the deck
        //and placing it on the table.
        //First card drawn on the table cannot be a wild card.
        while (table.isEmpty()) {
            if (getDeck().get(getDeckNum() - 1).isWildCard()) {
                shuffleDeck(getDeck());
            } else if (getDeck().get(getDeckNum() - 1).isActionCard()) {
                table.add(draw());
                initAction(p1.getPlayerOneHand(), table.get(0));
            } else {
                table.add(draw());
            }
        }

        //big change may have been made...must check all case where playerTurn is being called and where
        //the second parameter is used.
        //Keeps the game going until someone wins or the deck runs out of cards.
        while (!p1.getPlayerOneHand().isEmpty() && !bot.getAIHand().isEmpty()) {
            for (ArrayList<Card> cards : playerList) {
                shuffleDeck(getDeck());
                //System.out.println("Deck size: " + deck.getDeckNum() + " \n");
                deck.display(table, "table");

                if (cards == p1.getPlayerOneHand()) {
                    playerTurn(table, bot.getAIHand());
                    checkWinner(p1.getHandSize(), "you");
                } else if (cards == bot.getAIHand()) {
                    bot.playerTurn(table, p1.getPlayerOneHand());
                    checkWinner(bot.getAIHand().size(), "AI");
                }
            }
        }
    }

    //p1's playerTurn
    public void playerTurn(ArrayList<Card> table, ArrayList<Card> player) {

        deck.display(p1.getPlayerOneHand(), "hand");
        int temp = table.size();

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
                    case "N":

                        System.out.println("Which cards would you like to play? ");
                        System.out.println("====================================");
                        String input = scanner.nextLine();
                        String[] stringArray = input.split(", ");

                        for (String s : stringArray) {
                            char color = s.charAt(0);
                            int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
                            for (Card c : p1.getPlayerOneHand()) {
                                if (c.getColour() == color && c.getRank() == value) {
                                    if (checkValidity(table, c, play)) {
                                        table.add(0, c);
                                        p1.getPlayerOneHand().remove(c);
                                        break;
                                    } else {
                                        System.out.println("Invalid move!");
                                        break;
                                    }
                                }
                            }
                        }
                        return;


                    case "S":

                        System.out.println("Which cards would you like to play? ");
                        System.out.println("====================================");

                        String inputX = scanner.nextLine();

                        String[] stringArrayX = inputX.split(", ");

                        for (String arrayX : stringArrayX) {

                            for (int i = 0; i < p1.getPlayerOneHand().size(); i++) {
                                if (p1.getPlayerOneHand().get(i).isWildCard()) {
                                    if (arrayX.equals(p1.getPlayerOneHand().get(i).getWildCard())) {
                                        specialEffect(player, table, p1.getPlayerOneHand().get(i), "wildCard");
                                        table.add(p1.getPlayerOneHand().get(i));
                                        p1.getPlayerOneHand().remove(p1.getPlayerOneHand().get(i));
                                        if (arrayX.equals("Change colour")) {
                                            deck.display(table, "table");
                                            playerTurn(table, playerList.get(0));
                                            break;
                                        }
                                    }
                                }
                                if (p1.getPlayerOneHand().get(i).isActionCard()) {
                                    System.out.println("check one");
                                    if (arrayX.charAt(0) == p1.getPlayerOneHand().get(i).getColour()) {
                                        System.out.println("Check two");
                                        if (arrayX.substring(2).equals(p1.getPlayerOneHand().get(i).getActionCard())) {
                                            System.out.println("Single play");
                                            if (stringArrayX.length == 1) {
                                                System.out.println("check size");
                                                if (checkValidity(table, p1.getPlayerOneHand().get(i), "s")) {
                                                    System.out.println("single play check");
                                                    table.add(0, p1.getPlayerOneHand().get(i));
                                                    specialEffect(player, table, p1.getPlayerOneHand().get(i), "actionCard");
                                                    p1.getPlayerOneHand().remove(p1.getPlayerOneHand().get(i));
                                                    break;
                                                }
                                            //try implementing 2 forloop to go through the play because this algorithm
                                                //finds the first card (single) then the second (multi-play) but doesnt'
                                                //pursue it
                                                //try 2 checks comparing user input and the card on hand 
                                            } else if (checkValidity(table, p1.getPlayerOneHand().get(i), "s")) {
                                                System.out.println("multi-play check");
                                                table.add(0, p1.getPlayerOneHand().get(i));
                                                System.out.println("added");
                                                specialEffect(player, table, p1.getPlayerOneHand().get(i), "actionCard");
                                                p1.getPlayerOneHand().remove(p1.getPlayerOneHand().get(i));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                      //  drawMessage(temp, drawCount, "AI");
//                        if (temp < table.size()) {
//                            for (Card card : table) {
//                                if (card.isActionCard() && card.getActionCard().equals("Draw two")) {
//                                    drawCount += 2;
//                                } else if (card.isWildCard() && card.getWildCard().equals("Draw four")) {
//                                    drawCount += 4;
//                                } else {
//                                    break;
//                                }
//                            }
//                            System.out.println("AI drew " + drawCount + " cards");
//                        }
                        return;
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
    private void specialEffect(ArrayList<Card> hand, ArrayList<Card> table, Card card, String special) {
        if (special.equals("actionCard")) {
            switch (card.getActionCard()) {
                case "Skip turn":
                    playerTurn(table, playerList.get(0));
                    break;
                case "Reverse":
                    return;
                case "Draw two":
                    for(int i = 0; i < 2; i++){
                        hand.add(deck.draw());
                    }
                    break;

                default:
                    break;
            }
        } else if (special.equals("wildCard")) {
            System.out.println("2");
            switch (card.getWildCard()) {
                case "Change colour":
                    System.out.println("What colour would you like?\n");
                    String input = colourChange.nextLine();
                    char colour = input.charAt(0);

                    int i = 0;
                    while (i < deck.getColour().length) {
                        if (colour == deck.getColour()[i]) {
                            table.get(0).setColour(colour);
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
    private void initAction(ArrayList<Card> hand, Card card){
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
    private Boolean checkValidity(ArrayList<Card> table, Card card, String play) {

        char tableColour = table.get(0).getColour();
        int tableRank = table.get(0).getRank();
        String actionCard = table.get(0).getActionCard();
        if (play.equals("n")) {
            if (card.getColour() == tableColour && card.getRank() == tableRank) {
                return true;
            } return(card.getRank() == tableRank || card.getColour() == tableColour);

        } else if (play.equals("s")) {
            if (card.getColour() == tableColour && card.getActionCard().equals(actionCard)) {
                return true;
            } else if (card.getColour() != tableColour && card.getActionCard().equals(actionCard)) {
                return true;
            } return (card.getColour() == tableColour);

        }
        return false;
    }

//    private void drawMessage(int temp, int count, String player){
//        if (temp < table.size()) {
//            for (Card card : table) {
//                if (card.isActionCard() && card.getActionCard().equals("Draw two")) {
//                    count += 2;
//                } else if (card.isWildCard() && card.getWildCard().equals("Draw four")) {
//                    count += 4;
//                } else {
//                    break;
//                }
//            }
//            System.out.println(player + " drew " + count + " cards");
//        }
//    }

    //Checks for winner
    private Boolean checkWinner(int size, String player){
        if(size == 0) {
            switch (player){
                case "you":
                    System.out.println("You win!");
                    return true;
                case "AI":
                    System.out.println("AI wins :(");
                    return true;
                default:
                    break;
            }

        }
        return false;
    }

    public ArrayList<ArrayList<Card>> getPlayerList() { return playerList; }

    public ArrayList<Card> getTable(){
        return table;
    }
}
