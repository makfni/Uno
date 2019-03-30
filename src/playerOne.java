import java.util.ArrayList;

public class playerOne extends Uno{

    private ArrayList<Card> playerOneHand = new ArrayList<Card>();

    public playerOne(ArrayList<Card> playerOneHand) {
        this.playerOneHand = playerOneHand;
    }

    //Calls dealHand() from Deck and populates player hand
    public ArrayList<Card> getHand(Deck deck){
        playerOneHand.addAll(deck.dealHand());
        return playerOneHand;
    }

    public ArrayList<Card> getPlayerOneHand() {
        return playerOneHand;
    }

    public void setPlayerOneHand(ArrayList<Card> playerOneHand) {
        this.playerOneHand = playerOneHand;
    }

}
