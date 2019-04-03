import java.util.ArrayList;

public class AI extends Deck{
    private ArrayList<Card> AIHand = new ArrayList<>();
    private ArrayList<AI> bots = new ArrayList<>();

    public AI() {
        this.AIHand = AIHand;
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
