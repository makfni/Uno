package Java;

import java.util.ArrayList;

public class AI extends Deck implements PlayerInterface{
    private ArrayList<Card> AIHand = new ArrayList<>();
    //private ArrayList<AI> bots = new ArrayList<>();
    public Uno uno;
    public playerOne p1;


    public void playerTurn(ArrayList<Card> table, ArrayList<Card> player) {
        display(AIHand, "AI");
        for (int i = 0; i < AIHand.size(); i++) {
            //Check if there is a winner
            if (checkWinner(AIHand.size())) {
                System.exit(0);
            } else {
                for (Card t : table) {
                     if (AIHand.get(i).isWildCard()){
                         System.out.println("drawfour1");
                         specialEffect(p1.getPlayerOneHand(), AIHand.get(i), "wildCard");
                         table.add(AIHand.get(i));
                         AIHand.remove(AIHand.get(i));
                         return;
                     }
                     //change the for each into a forloop t.get(0)
                    if (t.getColour() == AIHand.get(i).getColour() && AIHand.get(i).isActionCard()) {
                        if (AIHand.get(i).isActionCard()) {
                            System.out.println("action1");
                            specialEffect(p1.getPlayerOneHand(), AIHand.get(i), "actionCard");
                            table.add(0, AIHand.get(i));
                            AIHand.remove(AIHand.get(i));
                            return;
                        }
                    } else if (t.getColour() == AIHand.get(i).getColour() && !AIHand.get(i).isActionCard()) {
                        System.out.println("rank1");
                        table.add(0, AIHand.get(i));
                        AIHand.remove(AIHand.get(i));
                        return;
                    } else {
                        AIHand.add(draw());
                        System.out.println("AI had to draw one card.");
                        return;
                    }
                }
            }
        }
    }



//        for(int i = 0; i < AIHand.size(); i++) {
//            for (Card t : table) {
//                if (AIHand.get(i).toString().equals(AIHand.get(i).getWildCard())) {
//                    specialEffect(p1.getPlayerOneHand(), AIHand.get(i), "wildCard");
//                    table.add(AIHand.get(i));
//                    AIHand.remove(AIHand.get(i));
//                } else if (AIHand.get(i).getColour() == t.getColour()) {
//                    if (AIHand.get(i).isActionCard()) {
//                        specialEffect(p1.getPlayerOneHand(), AIHand.get(i), "actionCard");
//                        table.add(AIHand.get(i));
//                        AIHand.remove(AIHand.get(i));
//                    }
//                }
//            }
//        }


//            //look at hand and decide which card to play
//            //wild card takes priority, then action, then normal
//        }
//
//



    public void specialEffect(ArrayList<Card> hand, Card card, String special){
        if (special.equals("actionCard")) {
                switch(card.getActionCard()){
                    case "Reverse":
                        System.out.println("AI reverse the play.");
                        return;
                    case "Skip turn":
                        playerTurn(uno.getTable(), uno.getPlayerList().get(1));
                        break;
                    case "Draw two":
                        for(int i = 0; i < 2; i++) {
                            hand.add(draw());
                        }
                        break;
                    default:
                        break;
                }


//            else if(c.getActionCard().equals("Draw two")){
//                    for(int i = 0; i < uno.getTable().size(); i++) {
//                        if (uno.getTable().get(i).getActionCard().equals("Draw two") && uno.getTable().get(i + 1).getActionCard().equals("Draw two")) {
//                            for (int j = 0; j < i * 2; j++) {
//                                hand.add(draw());
//                            }
//                        }else{
//                            for(int j = 0; j < 2; j++){
//                                hand.add(draw());
//                            }
//                        }
//                    }
//                }
//            }
        }
        else if (special.equals("wildCard")) {
            switch (card.getWildCard()) {
                case "Draw four":
                    for(int i = 0; i < 4; i++){
                        hand.add(draw());
                    }
                    break;
                case "Change colour":
                    for(int i = 0; i < AIHand.size(); i++){
                        for(int j = 0; j < 8; j++){
                            if(AIHand.get(i).getRank() == AIHand.get(i+1).getRank()) {
                                uno.getTable().get(0).setColour(AIHand.get(i).getColour());
                            }else{
                                uno.getTable().get(0).setColour(AIHand.get(i).getColour());
                                break;
                            }
                        }
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

//
//    public void specialEffect(ArrayList<Card> hand, Card card, String special) {
////        if (special.equals("actionCard")) {
////            for(Card c : AIHand){
////                if(c.getActionCard().equals("Reverse")){
////                    System.out.println("AI reversed the play.");
////                    return;
////                }else if(c.getActionCard().equals("Skip turn")){
////                    System.out.println("AI skipped your turn!");
////                    playerTurn(uno.getTable(), AIHand);
////                }else if(c.getActionCard().equals("Draw two")){
////                    for(int i = 0; i < uno.getTable().size(); i++) {
////                        if (uno.getTable().get(i).getActionCard().equals("Draw two") && uno.getTable().get(i + 1).getActionCard().equals("Draw two")) {
////                            for (int j = 0; j < i * 2; j++) {
////                                hand.add(draw());
////                            }
////                        }else{
////                            for(int j = 0; j < 2; j++){
////                                hand.add(draw());
////                            }
////                        }
////                    }
////                }
////            }
////        } else if (special.equals("wildCard")) {
////            for(Card c : AIHand){
////                if(uno.getTable().get(0).getColour() == c.getColour()){
////                    for(int i = 0; i < 4; i++){
////                        hand.add(0, uno.deck.draw());
////                    }
////                }else{
////                    for(int i = 0; i < AIHand.size(); i++){
////                        for(int j = 0; j < 8; j++){
////                            if(AIHand.get(i).getRank() == AIHand.get(i+1).getRank()) {
////                                uno.getTable().get(0).setColour(AIHand.get(i).getColour());
////                            }
////                        }
////                    }
////                }
////            }
////        }
//    }
//
//    //Checks for winner
//    public Boolean checkWinner(int size){
//        if(size == 0) {
//            System.out.println("You win!");
//            return true;
//        }
//        return false;
//    }
//
//
//    /*
//
//    //Depending on whether it's an action or wild card, this method will
//    //affect your opponent..or you.
//    public void specialEffect(ArrayList<Card> hand, Card card, String special) {
//        if (special.equals("actionCard")) {
//            switch (card.getActionCard()) {
//                case "Skip turn":
//                    playerTurn(table, playerList.get(0));
//                    break;
//                case "Reverse":
//                    return;
//                case "Draw two":
//                    for (int i = 0; i < 2; i++) {
//                        hand.add(deck.draw());
//                    }
//                    break;
//                default:
//                    break;
//            }
//        } else if (special.equals("wildCard")) {
//            switch (card.getWildCard()) {
//                case "Change colour":
//                    System.out.println("What colour would you like?\n");
//                    String input = colourChange.nextLine();
//                    char colour = input.charAt(0);
//
//                    int i = 0;
//                    while (i < deck.getColour().length) {
//                        if (colour == deck.getColour()[i]) {
//                            table.get(table.size() - 1).setColour(colour);
//                            break;
//                        }
//                        i++;
//                    }
//                    break;
//                case "Draw four":
//                    for (int j = 0; j < 4; j++) {
//                        hand.add(deck.draw());
//                    }
//                    break;
//                default:
//                    break;
//
//            }
//        }
//    }
//
//
//    //Similar to specialEffect() except this method is strictly for
//    //initializing the table.
//    public void initAction(ArrayList<Card> hand, Card card){
//        switch (card.getActionCard()) {
//            case "Skip turn":
//                System.out.println("First card on the table made you skip your turn.\n");
//                bot.playerTurn(table, playerList.get(1));
//                break;
//            case "Reverse":
//                System.out.println("First card on the table made you Reverse.\n");
//                bot.playerTurn(table, playerList.get(1));
//                return;
//            case "Draw two":
//                for (int i = 0; i < 2; i++) {
//                    hand.add(draw());
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//
//    //This method will check to see if the card you decided to play
//    //is a valid move according to the card on top of the pile
//    public Boolean checkValidity(ArrayList<Card> table, Card card, String play) {
//
//        char tableColour = table.get(table.size() - 1).getColour();
//        int tableRank = table.get(table.size() - 1).getRank();
//        String actionCard = table.get(table.size() - 1).getActionCard();
//
//        if (play.equals("n")) {
//            return card.getColour() == tableColour || card.getRank() == tableRank;
//        } else if (play.equals("s")) {
//            return card.getColour() == tableColour || card.getActionCard().equals(actionCard);
//        }
//        return false;
//    }
//
//    //Checks for winner
//    public Boolean checkWinner(int size){
//        if(size == 0) {
//            System.out.println("You win!");
//            return true;
//        }
//        return false;
//    }
//
//
//     */
//    public ArrayList<Card> getAIHand() {
//        return AIHand;
//    }
//
//    public int handSize(ArrayList<Card> hand){
//        return AIHand.size();
//    }
//
//}
