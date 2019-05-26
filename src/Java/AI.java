package Java;

import java.util.ArrayList;

public class AI extends Deck implements PlayerInterface {
    private ArrayList<Card> AIHand = new ArrayList<>();
    //private ArrayList<AI> bots = new ArrayList<>();
    public Uno uno;
    public int countB;
    public int countR;
    public int countY;
    public int countG;
    public int DTCounter = 1;
    public int revCounter = 1;
    public int skipCounter = 1;

    public void playerTurn(ArrayList<Card> table, ArrayList<Card> player) {
        display(AIHand, "AI");
        int temp = AIHand.size();

        //Check if there is a winner
        if (checkWinner(AIHand.size())) {
            System.exit(0);
        } else {
            for (int i = 0; i < AIHand.size(); i++) {
                if (AIHand.get(i).isWildCard()) {
                    switch (AIHand.get(i).getWildCard()) {
                        case "Draw four":
                            table.add(AIHand.get(i));
                            AIHand.remove(AIHand.get(i));

                            for (int j = 0; j < 4; j++) {
                                player.add(draw());
                                System.out.println("draw");
                            }
                            System.out.println("AI made you draw FOUR cards.");
                            return;
                        case "Change colour":

                            table.add(AIHand.get(i));
                            AIHand.remove(AIHand.get(i));
                            for (int j = 0; j < AIHand.size(); j++) {
                                if (AIHand.get(j).isActionCard()) {
                                    table.get(0).setColour(AIHand.get(j).getColour());
                                    System.out.println("here");
                                    break;
                                } else {
                                    for (Card card : AIHand) {
                                        switch (card.getColour()) {
                                            case 'B':
                                                countB++;
                                                break;

                                            case 'Y':
                                                countY++;
                                                break;

                                            case 'R':
                                                countR++;
                                                break;

                                            case 'G':
                                                countG++;
                                                break;
                                        }
                                    }
                                    int[] arr = {countB, countY, countR, countG};
                                    for (int x = 0; x < arr.length; x++) {
                                        for (int y = 1; y < arr.length; y++) {
                                            if (arr[y] > arr[x]) {
                                                table.get(0).setColour(getColour()[y]);
                                            } else {
                                                table.get(0).setColour(getColour()[x]);
                                            }
                                        }
                                    }
                                    System.out.println("there");
                                }
                            }
                    }
                    System.out.println("Colour have been changed to " + table.get(0).getColour());

                    playerTurn(table, AIHand);
                    return;
                }

            }
        }
        for (int i = 0; i < AIHand.size(); i++) {
            if (AIHand.get(i).isActionCard() && AIHand.get(i).getColour() == table.get(0).getColour()) {
                switch (AIHand.get(i).getActionCard()) {
                    case "Draw two":

                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));

                        for(int j = 0; j < AIHand.size(); j++){
                            if(AIHand.get(j).isActionCard())
                                if(AIHand.get(j).getActionCard().equals(table.get(0).getActionCard())){
                                table.add(0, AIHand.get(j));
                                AIHand.remove(AIHand.get(j));
                                DTCounter++;
                            }
                        }

                        for(int j = 0; j < DTCounter * 2; j++){
                            player.add(draw());
                        }

                        System.out.println("AI made you draw " + DTCounter * 2 + " cards!");

                        return;


                    case "Reverse":

                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));

                        for(int j = 0; j < AIHand.size(); j++){
                            if(AIHand.get(j).isActionCard())
                                if(AIHand.get(j).getActionCard().equals(table.get(0).getActionCard())){
                                    table.add(0, AIHand.get(j));
                                    AIHand.remove(AIHand.get(j));
                                    revCounter++;
                                }
                        }

                        if(revCounter % 2 == 0){
                             playerTurn(table, player);
                             return;
                        } else {
                            System.out.println("AI reversed the turns");
                            return;
                        }

                    case "Skip turn":
                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));

                        System.out.println();
                        playerTurn(table, player);

                        return;

                    default:
                        break;
                }
            } else {
                if (table.get(0).getRank() == AIHand.get(i).getRank()) {
                    table.add(0, AIHand.get(i));
                    AIHand.remove(AIHand.get(i));
                    for (int x = 0; x < AIHand.size(); x++) {
                        if (AIHand.get(x).getRank() == table.get(0).getRank()) {
                            table.add(0, AIHand.get(x));
                            AIHand.remove(AIHand.get(x));
                        }
                    }
                    return;
                } else if (table.get(0).getColour() == AIHand.get(i).getColour() && !AIHand.get(i).isActionCard()) {
                    table.add(0, AIHand.get(i));
                    AIHand.remove(AIHand.get(i));
                }
            }
        }

        if(AIHand.size() == temp) {
            AIHand.add(0, draw());
            System.out.println("AI had to draw a card from the deck");
        }
    }

    public void specialEffect(ArrayList<Card> hand, Card card, String special){
        if (special.equals("actionCard")) {
            switch (card.getActionCard()) {
                case "Reverse":
                    System.out.println("AI reverse the play.");
                    return;
                case "Skip turn":
                    playerTurn(uno.getTable(), AIHand);
                    break;
                case "Draw two":
                    for (int i = 0; i < 2; i++) {
                        hand.add(draw());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    //Checks for winner
    public Boolean checkWinner(int size){
        if(size == 0) {
            System.out.println("You win!");
            return true;
        }
        return false;
    }

    public ArrayList<Card> getAIHand() {
        return AIHand;
    }

    public int handSize(ArrayList<Card> hand){
        return AIHand.size();
    }

}
