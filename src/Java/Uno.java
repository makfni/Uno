package Java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Uno extends Deck implements PlayerInterface{

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
    private void startGame() {

        deck.dealHands(p1.getPlayerOneHand());
        deck.dealHands(bot.getAIHand());
        table.add(deck.draw());

        for(int i = 0; i < playerList.size(); i++){
            playerList.add(i,playerList.get(i));
        }
        while(!winner){
            playerTurn(table, playerList);
        }
    }

    public void playerTurn(ArrayList<Card> table, ArrayList<Player> playerList){

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
                                    }else{
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
                                    table.add(c);
                                    p1.getPlayerOneHand().remove(c);
                                    break;

                                } else if (c.getColour() == colour && ability.equals(c.getAbility())) {
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

//    play one card matching the discard in color, number, or symbol
//    play a Wild card, or a playable Wild Draw Four card (see restriction below)
//    draw the top card from the deck, then play it if possible
//
//    A player who draws from the deck must either play or keep that card and may play no other card from their hand on that turn.
//    A player may play a Wild card at any time, even if that player has other playable cards.
//    A player may play a Wild Draw Four card only if that player has no cards matching the current color. The player may have cards of a different color matching the current number or symbol or a Wild card and still play the Wild Draw Four card.[4] A player who plays a Wild Draw Four may be challenged by the next player in sequence (see Penalties) to prove that their hand meets this condition.
//    If the entire deck is used during play, the top discard is set aside and the rest of the pile is shuffled to create a new deck. Play then proceeds normally.
//    It is illegal to trade cards of any sort with another player.
//
//    A player who plays their next-to-last-card must call "Uno" as a warning to the other players.[5]
//
//    The first player to get rid of their last card ("going out") wins the hand and scores points for the cards held by the other players. Number cards count their face value, all action cards count 20, and Wild and Wild Draw Four cards count 50. If a Draw Two or Wild Draw Four card is played to go out, the next player in the sequence must draw the appropriate number of cards before the score is tallied.
//
//    The first player to score 500 points wins the game.

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
