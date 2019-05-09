package Java;

import java.util.ArrayList;

public class AI extends Deck{
    private ArrayList<Card> AIHand = new ArrayList<>();
    private ArrayList<AI> bots = new ArrayList<>();
    private Deck deck = new Deck();


    public void playerTurn(ArrayList<Card> table, ArrayList<Card> player) {
        deck.display(AIHand, "AI");

        System.exit(0);
    }

    public ArrayList<Card> getAIHand() {
        return AIHand;
    }

    public int handSize(ArrayList<Card> hand){
        return AIHand.size();
    }

}
