package Java;

import java.util.ArrayList;

public class AI extends Deck implements PlayerInterface {
    private ArrayList<Card> AIHand = new ArrayList<>();
    //private ArrayList<AI> bots = new ArrayList<>();
    public Uno uno;
    private int countB;
    private int countR;
    private int countY;
    private int countG;

    public void playerTurn(ArrayList<Card> table, ArrayList<Card> player) {
        display(AIHand, "AI");
        int temp = AIHand.size();

        for (int i = 0; i < AIHand.size(); i++) {
            if (AIHand.get(i).isWildCard()) {
                switch (AIHand.get(i).getWildCard()) {
                    case "Draw four":
                        table.add(AIHand.get(i));

                        AIHand.remove(AIHand.get(i));

                        for (int j = 0; j < 4; j++) {
                            player.add(draw());
                        }
                        System.out.println("AI made you draw FOUR cards.\n");
                        return;
                    case "Change colour":

                        table.add(AIHand.get(i));
                        AIHand.remove(AIHand.get(i));

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
                                default:
                                    break;
                            }
                        }

                        int[] arr = {countB, countY, countR, countG};
                        int max = arr[0];
                        int index = 0;

                        for (int x = 0; x < arr.length; x++) {
                            if (max < arr[x]) {
                                max = arr[x];
                                index = x;
                            }
                        }

                        table.get(0).setColour(getColour()[index]);
                        System.out.println("Colour have been changed to " + table.get(0).getColour() + "\n");
                        playerTurn(table, AIHand);
                        return;
                }

            }
        }


        for (int i = 0; i < AIHand.size(); i++) {
            if (AIHand.get(i).isActionCard() && AIHand.get(i).getColour() == table.get(0).getColour()) {
                switch (AIHand.get(i).getActionCard()) {
                    case "Draw two":
                        int DTCounter = 1;
                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));

                        DTCounter = specialCounter(table, DTCounter);

                        for (int j = 0; j < DTCounter * 2; j++) {
                            player.add(draw());
                            System.out.println("draw");
                        }

                        System.out.println("AI made you draw " + DTCounter * 2 + " cards!\n");

                        return;
                    case "Reverse":

                        int revCounter = 1;
                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));

                        revCounter = specialCounter(table, revCounter);

                        if (revCounter % 2 == 0) {
                            System.out.println("AI reversed to go again.\n");
                            playerTurn(table, player);
                        } else {
                            System.out.println("AI reversed to your turn.\n");
                            return;
                        }
                    case "Skip turn":

                        int skipCounter = 1;
                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));

                        skipCounter = specialCounter(table, skipCounter);

                        if (skipCounter % 2 == 0) {
                            System.out.println("Skipped to player one's turn.");
                            return;
                        } else {
                            System.out.println("AI skipped your turn.");
                            playerTurn(table, player);

                        }
                    default:
                        break;
                }
            }
            if (AIHand.size() < temp) {
                return;
            }
        }

        for (int i = 0; i < AIHand.size(); i++) {
            if (!AIHand.get(i).isActionCard() && !AIHand.get(i).isWildCard()) {
                if (table.get(0).getRank() == AIHand.get(i).getRank() && !AIHand.get(i).isActionCard()) {

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
                    return;
                }
            }
        }
        if (AIHand.size() == temp) {
            AIHand.add(0, draw());
            System.out.println("AI had to draw a card from the deck\n");
        }
    }

    private int specialCounter(ArrayList<Card> table, int counter) {
        for(int j = 0; j < AIHand.size(); j++){
            if(AIHand.get(j).isActionCard())
                if(AIHand.get(j).getActionCard().equals(table.get(0).getActionCard())){
                    table.add(0, AIHand.get(j));
                    AIHand.remove(AIHand.get(j));
                    counter++;
                }
        }
        return counter;
    }

    //Checks for winner
    public Boolean checkWinner(int size){
        if(size == 0) {
            System.out.println("AI win!\n");
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
