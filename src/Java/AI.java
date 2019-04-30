package Java;

import java.util.ArrayList;

public class AI extends Deck implements PlayerInterface{
    private ArrayList<Card> AIHand = new ArrayList<>();
    private ArrayList<AI> bots = new ArrayList<>();
    Deck deck = new Deck();

    public AI() {
        this.AIHand = AIHand;
    }

    public void playerTurn(ArrayList<Card> field, ArrayList<Player> playerList){
        deck.display(AIHand, "AI");
        System.exit(0);
    }

    public ArrayList<Card> getAIHand() {
        return AIHand;
    }

    public ArrayList<AI> getBots(){ return bots; }

    public void setAIHand(ArrayList<Card> AIHand) {
        this.AIHand = AIHand;
    }

    public int handSize(ArrayList<Card> hand){
        return AIHand.size();
    }

}
