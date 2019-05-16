package Java;

import java.util.ArrayList;

public class AI extends Deck implements PlayerInterface{
    private ArrayList<Card> AIHand = new ArrayList<>();
    //private ArrayList<AI> bots = new ArrayList<>();
    public Uno uno;
    public playerOne p1;
    public int count = 1;
    public int countReverse;

    public void playerTurn(ArrayList<Card> table, ArrayList<Card> player) {
        display(AIHand, "AI");
        int temp = AIHand.size();
        int tempReverse;
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
                            }
                            System.out.println("AI made you draw FOUR cards.");
                            return;
                        case "Change colour":

                            table.add(AIHand.get(i));
                            AIHand.remove(AIHand.get(i));
                            for (int x = 0; x < AIHand.size(); x++) {
                                for (Card card : AIHand) {
                                    if (AIHand.get(x).getColour() == card.getColour()) {
                                        table.get(0).setColour(AIHand.get(x).getColour());
                                    }
                                }
                            }
                            System.out.println("Colour have been changed to " + table.get(0).getColour());

                            break;

                    }
                }
            }
            for (int i = 0; i < AIHand.size(); i++) {
                if (table.get(0).getColour() == AIHand.get(i).getColour() && AIHand.get(i).isActionCard()) {
                    System.out.println("action1");
                    switch (AIHand.get(i).getActionCard()) {
                        case "Reverse":
                            //check from even numbers, if num is even then it is AI turn else other player turn
                            if(table.get(0).getColour() == AIHand.get(i).getColour() || table.get(0).getActionCard().equals("Reverse")) {
                                table.add(0, AIHand.get(i));
                                AIHand.remove(AIHand.get(i));
                                return;
                            }
//                            for (int j = 0; j < AIHand.size(); j++) {
//                                if (AIHand.get(j).getActionCard().equals("Reverse")) {
//                                    table.add(0, AIHand.get(j));
//                                    AIHand.remove(AIHand.get(j));
//                                    countReverse++;
//                                }
//                            }
//                            for(int r = 0; r <= countReverse/2; r++){
//                                tempReverse = countReverse % r;
//                                if(tempReverse == 0){
//                                    playerTurn(table, AIHand);
//                                }else{
//                                    return;
//                                }
//                            }

                        case "Skip turn":
                            if(table.get(0).getColour() == AIHand.get(i).getColour() || table.get(0).getActionCard().equals("Skip turn")) {
                                System.out.println("AI skipped your turn.");
                                table.add(0, AIHand.get(i));
                                AIHand.remove(AIHand.get(i));
                                playerTurn(table, AIHand);
                            }
                        case "Draw two":
                            if(table.get(0).getColour() == AIHand.get(i).getColour() || table.get(0).getActionCard().equals("Draw two")) {
                                for (int j = 0; j < AIHand.size(); j++) {
                                    if (AIHand.get(i).getActionCard().equals(AIHand.get(j).getActionCard())) {
                                        table.add(0, AIHand.get(j));
                                        AIHand.remove(AIHand.get(j));
                                        count++;
                                    }
                                }
                                table.add(0, AIHand.get(i));
                                AIHand.remove(AIHand.get(i));
                                for (int j = 0; j < 2 * count; j++) {
                                    player.add(draw());
                                }
                                return;
                            }
                        default:
                            break;
                    }
                }
            }

            for(int i = 0; i < AIHand.size(); i++) {
                if (!AIHand.get(i).isActionCard() || !AIHand.get(i).isWildCard()) {
                    if (table.get(0).getRank() == AIHand.get(i).getRank()) {
                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));
                        for (int j = 0; j < AIHand.size(); j++) {
                            if (AIHand.get(i).getRank() == AIHand.get(j).getRank()) {
                                table.add(0, AIHand.get(j));
                                AIHand.remove(AIHand.get(j));
                            }
                        }

                    }else if (table.get(0).getColour() == AIHand.get(i).getColour()){
                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));
                       return;
                    }
                }
            }

            if (AIHand.size() == temp) {
                AIHand.add(0, draw());
                System.out.println("AI had to draw a card from the deck");
            }
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
        }else if (special.equals("wildCard")) {
            switch (card.getWildCard()) {
                case "Draw four":
                    System.out.println("drawfour");
                    for(int i = 0; i < 4; i++){
                        hand.add(draw());
                    }
                    break;
                case "Change colour":
                    System.out.println("changecolour");
//                    for(int i = 0; i < AIHand.size(); i++){
//                        for(int j = 0; j < AIHand.size(); j++){
//                            if(AIHand.get(i).getColour() == AIHand.get(j).getColour()) {
//                                uno.getTable().get(0).setColour(AIHand.get(i).getColour());
//                            }
//                        }
//                    }
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
