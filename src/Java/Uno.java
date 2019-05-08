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
    private Boolean winner = false;

    //startGame function to initialize game
    private void startGame() {

        deck.dealHands(p1.getPlayerOneHand());
        deck.dealHands(bot.getAIHand());
        table.add(deck.draw());

        //p1 is at index 0
        playerList.add(p1.getPlayerOneHand());
        //bot is at index 1
        playerList.add(bot.getAIHand());

        while(!winner){
            playerTurn(table, playerList);
        }
    }

    public void playerTurn(ArrayList<Card> table, ArrayList<ArrayList<Card>> playerList){
        for(ArrayList<Card> playerL : playerList ){
            System.out.println("Deck size: " + deck.getDeckNum());
            deck.display(table,"table");
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
                            input = input.replaceAll("\\[", "").replaceAll("\\]", "");
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


                            playerTurn(table, playerList);
                            break;

                        case "SPECIAL":

                            System.out.println("Which cards would you like to play? ");
                            System.out.println("====================================");

                            String inputX = scanner.nextLine();
                            input = inputX.replaceAll("\\[", "").replaceAll("\\]", "");
                            String[] stringArrayX = input.split(", ");

                            System.out.println(stringArrayX[0]);

                            for (String arrayX : stringArrayX) {

                                char colour = arrayX.charAt(0);
                                String ability = arrayX.substring(2);

                                for (Card c : p1.getPlayerOneHand()) {
                                    if (arrayX.equals(c.getAbilityX())) {
                                        specialEffect(p1.getPlayerOneHand(), c, "abilityX");
                                        p1.getPlayerOneHand().remove(c);
                                        break;

                                    } else if (c.getColour() == colour && ability.equals(c.getAbility())) {
                                        if (checkValidity(table, c, play)) {
                                            table.add(c);
                                            p1.getPlayerOneHand().remove(c);
                                            specialEffect(p1.getPlayerOneHand(), c, "ability");
                                            break;
                                        } else {
                                            System.out.println("Invalid move!");
                                        }
                                    }
                                }
                            }


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

    public void specialEffect(ArrayList<Card> hand, Card card, String special) {
        if (special.equals("ability")) {
            switch (card.getAbility()) {
                case "Skip turn":
                    playerTurn(table, playerList);
                    break;
                case "Reverse":
                    System.out.println("here");
                    bot.playerTurn(table, playerList);
                    break;
                case "Draw two":
                    for (int i = 0; i < 2; i++) {
                        playerList.get(1).add(deck.draw());
                    }
                    break;
                default:
                    break;
                }
        } else if (special.equals("abilityX")) {
            switch (card.getAbilityX()) {
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


    public Boolean checkValidity(ArrayList<Card> table, Card card, String play){

        char tableColour = table.get(table.size()-1).getColour();
        int tableRank = table.get(table.size()-1).getRank();
        String ability = table.get(table.size()-1).getAbility();

        if(play.equals("normal")){
            return card.getColour() == tableColour || card.getRank() == tableRank;
        }else if(play.equals("special")){
            return card.getColour() == tableColour || card.getAbility().equals(ability);
        }
        return false;
    }


    public ArrayList<Card> getTable(){
        return table;
    }
}
