import java.util.ArrayList;

public class AI extends Deck implements Player{
    private ArrayList<Card> AIHand = new ArrayList<>();
    private ArrayList<AI> bots = new ArrayList<>();

    public AI() {
        this.AIHand = AIHand;
    }

    //Calls dealHand() from Deck and populates player hand
    public ArrayList<Card> initAIHand(Deck deck){
        AIHand.addAll(deck.dealHand());
        return AIHand;
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
